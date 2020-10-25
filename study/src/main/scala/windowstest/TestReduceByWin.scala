package windowstest

import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
//5s内  记录日志的条数  根据 zb进行分组
object TestReduceByWin {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9999)
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })
    var windowedStream = dataStream.map(data=>(data.zb,1))
      .keyBy(_._1)
      .timeWindow(Time.seconds(5))
      .reduce((t1,t2)=>(t1._1,t1._2+t2._2))

    dataStream.print("dataString")

    windowedStream.print("测试 滚动时间窗口，的聚合函数reduce")
    env.execute()

  }

}
