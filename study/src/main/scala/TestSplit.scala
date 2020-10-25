import SensorReading.SensorReading
import org.apache.flink.streaming.api.scala._
object TestSplit {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment

    environment.setParallelism(1)
    val unit = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")
    var datdStream = unit.map(data=>{
      var dataArry= data.split(",")
      SensorReading(dataArry(0).trim,dataArry(1).trim.toLong,dataArry(2).trim.toDouble)
    })

    var dataSplitStream = datdStream.split(data=>{
      if(data.hot>300){
        Seq("high")
      }else{
        Seq("low")
      }
    })

    val high = dataSplitStream.select("high")
    val low = dataSplitStream.select("low")
    val all = dataSplitStream.select("high","low")

    var warnings = high.map(data=>(data.zb,data.hot))
    val connectedStreams = warnings.connect(low)
    var dataStream = connectedStreams.map(
      data1=>(data1._1,data1._2,"报警"),
      data2=>(data2.zb,"健康")
    )
    dataStream.print()

    /*high.print("high")
    low.print("low")
    all.print("all")*/
    var unionstream=high.union(low).union(all)
    unionstream.print()


    environment.execute("测试数据")
  }

}
