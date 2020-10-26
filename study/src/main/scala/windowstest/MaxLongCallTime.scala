package windowstest

import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._

//每个5s统计一下最近10s内  每个基站中通话时间最长的一次通话发生时间
//并且告诉当前发生的时间范围
object MaxLongCallTime {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9990)
    env.setParallelism(1)
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })


  }

}
