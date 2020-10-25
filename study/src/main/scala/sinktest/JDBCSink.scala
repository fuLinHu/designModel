package sinktest

import java.sql.{Connection, DriverManager, PreparedStatement}

import SensorReading.SensorReading
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala._

object JDBCSink {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)

    val inputstream = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")

    var dataString = inputstream.map(data=>{
      try{
        var dataArry = data.split(",")
        SensorReading(dataArry(0).trim, dataArry(1).trim.toLong, dataArry(2).trim.toDouble)
      }catch{
        case ex: Exception =>{
          SensorReading("0",0, 0.0)
        }
      }
    })
    dataString.print()

    dataString.addSink(new MyJdbcSink())

    environment.execute("test jdbc sink")

  }

}

class MyJdbcSink() extends RichSinkFunction[SensorReading]{
  var connection :Connection = _
  var insert:PreparedStatement = _
  var update:PreparedStatement = _

  override def open(parameters: Configuration): Unit = {
     super.open(parameters)
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf8","root","root")
     insert = connection.prepareStatement("insert into temperature (sensor,temp) values (?,?)")
     update = connection.prepareStatement("update  temperature set temp = ? where sensor = ? ")
  }

  override def invoke(value: SensorReading, context: SinkFunction.Context[_]): Unit = {
    update.setDouble(1,value.hot)
    update.setString(2,value.zb)
    update.execute()
    //如果查询不到
    if(update.getUpdateCount == 0){
      insert.setString(1,value.zb)
      insert.setDouble(2,value.hot)
      insert.execute()
    }

  }

  override def close(): Unit = {
    insert.close()
    update.close()
    connection.close()
  }
}