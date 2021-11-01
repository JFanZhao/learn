package org.apache.spark

import org.apache.spark.rpc.RpcEnv
import org.apache.spark.sql.SparkSession

/**
 * rpc server
 */
object RpcServerMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sparkSession = SparkSession.builder().config(conf).master("local[*]").appName("NX RPC").getOrCreate()
    val sparkContext = sparkSession.sparkContext
    val sparkEnv = sparkContext.env

    val rpcEnv = RpcEnv.create(HelloRpcSettings.getName(), HelloRpcSettings.getHostname(), HelloRpcSettings.getHostname(), HelloRpcSettings.getPort(), conf,
      sparkEnv.securityManager, 1, false)

    val helloEndPoint = new HelloEndPoint(rpcEnv)

    rpcEnv.setupEndpoint(HelloRpcSettings.rpcName,helloEndPoint)

    rpcEnv.awaitTermination()
  }

}
