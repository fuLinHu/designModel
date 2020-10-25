package windowstest

import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

//滚动窗口
object TumblingTimWindow {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })


  }

}
