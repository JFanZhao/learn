package org.apache.spark

import org.apache.spark.rpc.{RpcCallContext, RpcEndpoint, RpcEnv}

/**
 * endpoint 相当于actor
 */
class HelloEndPoint(override val rpcEnv: RpcEnv) extends RpcEndpoint {
  override def receive: PartialFunction[Any, Unit] = {
    case SayHi(msg)=>{
      println(s"Receive Message: $msg")
    }
  }

  override def receiveAndReply(context: RpcCallContext): PartialFunction[Any, Unit] = {
    case SayHi(msg)=>{
      println(s"Receive Message: $msg")
      context.reply(s"I'm Server, $msg")
    }
    case SayBye(msg)=>{
      println(s"Receive Message: $msg")
      context.reply(s"I'm Server, $msg")
    }
  }

  override def onStart(): Unit = {
    println(rpcEnv.address)
    println("Start HelloEndPoint")
  }

  override def onStop(): Unit = println("Stop The HelloEndPoint")
}

case class SayHi(message : String)
case class SayBye(message : String)
