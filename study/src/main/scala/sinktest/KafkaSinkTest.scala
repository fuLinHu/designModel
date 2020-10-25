package sinktest

import java.util.Properties

import SensorReading.SensorReading
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.runtime.FlinkActor
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaProducer010}

object KafkaSinkTest {
  def main(args: Array[String]): Unit = {
    val environment = StreamExecutionEnvironment.getExecutionEnvironment
    environment.setParallelism(1)

    var properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "flink-group")
    properties.setProperty("auto.offset.reset","latest")

    val inputstream = environment.addSource(new FlinkKafkaConsumer010[String]("sensor",new SimpleStringSchema(),properties))

    //val inputstream = environment.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt")
    var dataString = inputstream.map(data=>{
      try{
        var dataArry = data.split(",")
        SensorReading(dataArry(0).trim, dataArry(1).trim.toLong, dataArry(2).trim.toDouble).toString()
      }catch{
        case ex: Exception =>{
          "nothing printl"
        }
      }
    })
    dataString.print()
    dataString.addSink(new FlinkKafkaProducer010[String]("localhost:9092","sinkTest",new SimpleStringSchema()))
    environment.execute("kafka sink test")

  }

}
