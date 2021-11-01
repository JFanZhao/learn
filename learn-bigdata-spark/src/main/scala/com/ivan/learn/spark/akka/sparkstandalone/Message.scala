package com.ivan.learn.spark.akka.sparkstandalone

/**
 * 注册信息，spark worker->spark master
 * @param sparkWorkerId
 * @param memory
 * @param cpu
 */
case class RegisterSparkWorker(val sparkWorkerId: String, val memory: Int, val cpu: Int)

/**
 * 注册成功信息，master->worker
 * @param sparkMasterHostName
 */
case class RegisteredSparkWorker(val sparkMasterHostName:String)

/**
 * 信条信息，worker->master
 * @param sparkWorkerId
 */
case class Heartbeat(val sparkWorkerId:String)

/**
 * 注册信息，spark worker->spark master
 * @param sparkWorkerId
 * @param memory
 * @param cpu
 */
class SparkWorkerInfo(val sparkWorkerId: String, val memory: Int, val cpu: Int){
  var lastHeartBeatTime: Long = _

  override def toString: String = {
    sparkWorkerId + "," + memory + "," + cpu
  }
}

// TODO 注释： 一个发送心跳的信号
case object SendMessage

// TODO 注释： 一个检查信号
case object CheckTimeOut
