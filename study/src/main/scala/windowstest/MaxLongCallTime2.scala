package windowstest

import java.util.Date

import SensorReading.SensorReading
import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//每个5s统计一下最近10s内  每个基站中通话时间最长的一次通话发生时间
//并且告诉当前发生的时间范围
object MaxLongCallTime2 {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //设置时间语义
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    val text = env.socketTextStream("localhost", 9990)
    env.setParallelism(1)
    env.getConfig.setAutoWatermarkInterval(100l) //周期性的 watermark   100ms
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })
      // //第一种直接才用 AssignerWithPeriodicWatermarks 的实现类
      //引入watermater(数据乱序) 延迟时间是3s  周期性的时间100ms
      //.assignAscendingTimestamps(_.time)  //指定eventTime 是什么
      /*.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(3)) {
      override def extractTimestamp(element: SensorReading): Long = { //设置 eventtime
        element.time
      }
    })*/
    //第二种  定义一个实现类  AssignerWithPeriodicWatermarks  接口实现类
      .assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[SensorReading] {
      var maxEventTime :Long = _
      override def getCurrentWatermark: Watermark = { //设定周期性的生成  watermark
       // println("getCurrentWatermark :"+(maxEventTime - 3000l))
        new Watermark(maxEventTime - 3000l)  //迟来3000s 也就是  窗口有区间 加上  3000 触发
      }

      override def extractTimestamp(element: SensorReading, previousElementTimestamp: Long): Long ={ //设定 eventtime
        maxEventTime = maxEventTime.max(element.time)
        //println("maxEventTime:"+maxEventTime)
        element.time
      }
    })




    //分组 开窗
    var waterMark = dataStream.keyBy(_.zb).timeWindow(Time.seconds(10),Time.seconds(5))
      .reduce(new MyReduceFunction(),new ReturnMaxtWinFunction())
    .print("test 乱序流水线")
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
      sb.append("窗口范围：").append(window.getStart+" ："+new Date(window.getStart).toLocaleString).append("---------").append(window.getEnd+" ："+new Date(window.getEnd).toLocaleString)
      sb.append("\n")
      sb.append("坐标：").append(value.zb).append(" 时间：").append(value.time+" ："+new Date(window.getStart).toLocaleString).append(" 温度").append(value.hot)
      out.collect(sb.toString())
    }
  }

}
