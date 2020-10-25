package windowstest

import SensorReading.SensorReading
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//每隔3s 计算5s内  记录日志的条数  根据 zb进行分组（滑动窗口）
object TestAggreateByWin {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("localhost", 9990)
    var dataStream = text.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })
    var windowedStream = dataStream.map(data=>(data.zb,1))
      .keyBy(_._1)
        .window(SlidingProcessingTimeWindows.of(Time.seconds(10),Time.seconds(5)))
        .aggregate(new MyAggreFunction(),new MyWindFunction)
      /*.timeWindow(Time.seconds(5))
      .reduce((t1,t2)=>(t1._1,t1._2+t2._2))

    dataStream.print("dataString")*/
    dataStream.print("dataString")
    windowedStream.print("测试 滚动时间窗口，的聚合函数reduce")
    env.execute()

  }



}
/**
 * @Author 付林虎
 * @Description //TODO
 * @Date 2020/10/25 18:16
 * @Param
 * @Version V1.0
 * @return
 **/
class MyAggreFunction extends AggregateFunction[(String,Int),Long,Long]{
  override def createAccumulator(): Long = 0

  override def add(in: (String, Int), acc: Long): Long = acc+in._2  //来一条执行一次

  override def getResult(acc: Long): Long = acc  //窗口结束的时候执行一次

  override def merge(acc: Long, acc1: Long): Long = acc+acc1
}

///MyWindFunction 输入数据来自与  MyAggreFunction   在窗口执行结束  限制性AggregateFunction getResult 然后在执行
class MyWindFunction extends WindowFunction[Long,(String,Long),String,TimeWindow]{
  override def apply(key: String, window: TimeWindow, input: Iterable[Long], out: Collector[(String, Long)]): Unit = {
    out.collect(key,input.iterator.next()) //迭代器种  只有一个值
  }
}