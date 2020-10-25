import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._


object TestReduce {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment

    environment.setParallelism(1)
    val unit = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")
    var dataString = unit.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)

    }).keyBy(0).
      //时间戳 上一次得加1  作为本次的数据，  温度是 当前得加10 也就是 读到的这一行数据
      reduce((x,y)=> SensorReading(x.zb,x.time+1,y.hot+10))
    dataString.print()
    environment.execute()

  }
}
