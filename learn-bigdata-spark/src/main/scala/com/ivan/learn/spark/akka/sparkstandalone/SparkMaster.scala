package com.ivan.learn.spark.akka.sparkstandalone

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

/**
 * spark master
 *
 * @param hostname
 * @param port
 */
class SparkMaster(var hostname: String, val port: Int) extends Actor {
  //存储work id和work info的map对象
  private var id2SparkWorkerInfoMap = new mutable.HashMap[String, SparkWorkerInfo]()
  //spark work info集合
  private var sparkWorkerInfoSet = new mutable.HashSet[SparkWorkerInfo]()

  override def receive: Receive = {
    /**
     * 注册信息
     */
    case RegisterSparkWorker(sparkWorkerId, memory, cpu) => {
      val sparkWorkerInfo = new SparkWorkerInfo(sparkWorkerId, memory, cpu)
      println(s"节点 ${sparkWorkerId} 上线")
      sparkWorkerInfoSet += sparkWorkerInfo
      id2SparkWorkerInfoMap.put(sparkWorkerInfo.sparkWorkerId,sparkWorkerInfo)
      //发送注册完成消息
      sender() ! RegisteredSparkWorker(hostname+":"+port)
    }

    /**
     * 处理心跳
     */
    case Heartbeat(sparkWorkerId)=>{
      val currentTime = System.currentTimeMillis()
      val sparkWorkerInfo = id2SparkWorkerInfoMap(sparkWorkerId)
      sparkWorkerInfo.lastHeartBeatTime = currentTime

      id2SparkWorkerInfoMap(sparkWorkerId) = sparkWorkerInfo
      sparkWorkerInfoSet += sparkWorkerInfo
    }

    //检查实现的worker节点，15秒还没做心跳的即为失效
    case CheckTimeOut => {
      val currentTime = System.currentTimeMillis()
      sparkWorkerInfoSet.filter(workerInfo => {
        val heartbeatTimeout = 15000
        val bool = currentTime - workerInfo.lastHeartBeatTime > heartbeatTimeout
        if (bool) {
          println(s"节点 ${workerInfo.sparkWorkerId} 下线")
        }
        bool
      }).foreach(deadWorkInfo => {
        sparkWorkerInfoSet -= deadWorkInfo;
        id2SparkWorkerInfoMap.remove(deadWorkInfo.sparkWorkerId)
      })
      println("当前注册成功的节点数" + sparkWorkerInfoSet.size + "\t分别是：" + sparkWorkerInfoSet.map(x => x.toString)
        .mkString(","));
    }
  }

  /**
   * 启动时执行，每五秒执行一次调度，检查失效的worker节点
   */
  override def preStart(): Unit = {
    import scala.concurrent.duration._
    import context.dispatcher

    context.system.scheduler.schedule(0 millis, 5000 millis, self, CheckTimeOut)
  }
}

object SparkMaster {
  def main(args: Array[String]): Unit = {

    // TODO_MA 注释： 地址参数
    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = localhost
         |akka.remote.netty.tcp.port = 6789
      """.stripMargin
    val conf = ConfigFactory.parseString(str)

    // TODO_MA 注释：ActorSystem
    val actorSystem = ActorSystem(Constant.SMAS, conf)

    // TODO_MA 注释：启动了一个actor ： SparkMaster
    actorSystem.actorOf(Props(new SparkMaster("localhost", 6789)), Constant.SMA)


  }
}
