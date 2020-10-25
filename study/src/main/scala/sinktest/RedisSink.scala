package sinktest

import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

object RedisSink {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)

    val inputstream = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")

    var dataString = inputstream.map(data=>{
      try{
        var dataArry = data.split(",")
        SensorReading(dataArry(0).trim, dataArry(1).trim.toLong, dataArry(2).trim.toDouble)
      }catch{
        case ex: Exception =>{
          SensorReading("0",0, 0.0)
        }
      }
    })
    dataString.print()

    val conf =  new FlinkJedisPoolConfig.Builder()
      .setHost("localhost")
        .setPort(6379)
        .build()



    dataString.addSink(new RedisSink(conf,new MyredisMapper()))

    environment.execute("redis sink test")

  }

}
class MyredisMapper() extends RedisMapper[SensorReading]{
  //保存到rdis的命令
  override def getCommandDescription: RedisCommandDescription = {
    //保存成 hash表
    new RedisCommandDescription(RedisCommand.HSET,"温度传感器")
  }

  override def getKeyFromData(t: SensorReading): String = {
    //定义保存到redis key
    t.zb
  }

  override def getValueFromData(t: SensorReading): String = {
    //定义保存到redis value
    t.hot.toString
  }
}