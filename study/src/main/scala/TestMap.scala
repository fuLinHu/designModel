

import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._


object TestMap {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment

    environment.setParallelism(1)
    val unit = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")
    var dataString = unit.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    }).keyBy(0).sum(2)
    //dataString.keyBy(_.zb) 其返回的是指定类型  String
   /* val value:KeyedStream[SensorReading,String] = dataString.keyBy(_.zb)
    val value1 = value.sum(2)
    value1.print()*/
    dataString.print()
    environment.execute()

  }
}
