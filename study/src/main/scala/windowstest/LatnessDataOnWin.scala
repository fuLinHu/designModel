package windowstest

import SensorReading.SensorReading
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector



object LatnessDataOnWin {

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
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[SensorReading](Time.seconds(2)) { //水位线延迟两秒
        override def extractTimestamp(element: SensorReading): Long = {
          element.time
        }
      })


    //定义一个测输出流标签
    var lateTag = new OutputTag[SensorReading]("late")
    val result = dataStream.keyBy(_.zb)
      .timeWindow(Time.seconds(10), Time.seconds(5))
      //设置迟到数据超出2秒得情况下怎么办  交给 alloedloateness
      //也分为两种情况
      //1. 允许数据迟到5s （迟到 2-5）再次触发窗口函数  触发得条件   watermater < end-of-wid  + allowedlatennesss
      //第二种  迟到得数据在  5 秒以上  输出到测流中
      .allowedLateness(Time.seconds(5)) //最多迟到我5s中  还可以触发窗口
      .sideOutputLateData(lateTag)
      .aggregate(new MyAggrCountFunction, new OutPutResultWindFunction)
    result.getSideOutput(lateTag).print("late数据：") //  迟到超过5s数据  本来需要在计算，在实际业务中要另外处理
    result.print("mian")
    env.execute()

  }




  //增量聚合函数
  class MyAggrCountFunction extends  AggregateFunction[SensorReading,Long,Long]{
    override def createAccumulator(): Long = 0

    override def add(in: SensorReading, acc: Long): Long = acc+1

    override def getResult(acc: Long): Long = acc

    override def merge(acc: Long, acc1: Long): Long = acc+acc1
  }

  class OutPutResultWindFunction extends WindowFunction[Long,String,String,TimeWindow]{
    override def apply(key: String, window: TimeWindow, input: Iterable[Long], out: Collector[String]): Unit = {
      var value = input.iterator.next()
      var sb = new StringBuilder
      sb.append("窗口范围").append(window.getStart).append("-----").append(window.getEnd)
        .append("\n")
        .append("当前得基站得ID：").append(key).append("呼叫得数量是：").append(value)

    }
  }



}
