package windowstest

import SensorReading.SensorReading
import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//每个5s统计一下最近10s内  每个基站中通话时间最长的一次通话发生时间
//并且告诉当前发生的时间范围
object MaxLongCallTime {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //设置时间语义
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val text = env.socketTextStream("localhost", 9990)
    env.setParallelism(1)
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })
    //引入watermater(数据有序)
      .assignAscendingTimestamps(_.time)  //指定eventTime 是什么
    //分组 开窗
    var waterMark = dataStream.keyBy(_.zb).timeWindow(Time.seconds(10),Time.seconds(5))
      .reduce(new MyReduceFunction(),new ReturnMaxtWinFunction())
    .print("test 顺序流水线")
    env.execute()


  }

  class MyReduceFunction extends ReduceFunction[SensorReading]{
    override def reduce(t: SensorReading, t1: SensorReading): SensorReading = {
      if(t.hot>t1.hot){
        t
      }else{
        t1
      }
    }
  }
  //IN, OUT, KEY, W <: Window

  class ReturnMaxtWinFunction extends WindowFunction[SensorReading,String,String,TimeWindow]{//窗口触发的时候执行
    override def apply(key: String, window: TimeWindow, input: Iterable[SensorReading], out: Collector[String]): Unit = {
      var value = input.iterator.next()
      var sb = new StringBuilder
      sb.append("窗口范围：").append(window.getStart).append("---------").append(window.getEnd)
      sb.append("\n")
      sb.append("坐标：").append(value.zb).append(" 时间：").append(value.time).append(" 温度").append(value.hot)
      out.collect(sb.toString())
    }
  }

}
