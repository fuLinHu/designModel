package sinktest

import java.util

import SensorReading.SensorReading
import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink
import org.apache.http.HttpHost
import org.elasticsearch.client.Requests

object ESsink {
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
    val httpHost = new util.ArrayList[HttpHost]()

    httpHost.add(new HttpHost("localhost", 9200, "http"));


    val esSinkBuidler =   new ElasticsearchSink.Builder[SensorReading](
      httpHost,
      new ElasticsearchSinkFunction[SensorReading] {
        override def process(t: SensorReading, runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
          println("save data starting")
          var json = new util.HashMap[String,String]()

          /*id100,1603069986314,200.4
          id101,1602983586314,300.5
          id101,1602897186315,100.6
          id101,1602810786318,400.7
          id104,1602724386314,300.8
          id105,1603069986314,600.7
          id105,1603069986314,500.7*/

          json.put("zb",t.zb)
          json.put("hot",t.hot.toString)
          json.put("time",t.time.toString)

          //创建index
          val indexRequest = Requests.indexRequest().index("sensor").source(json)

          //利用 requestIndexer 发送请求写入数据
          requestIndexer.add(indexRequest)
          println("save data ending")
        }
      }
    )


    dataString.addSink(esSinkBuidler.build())


    environment.execute("ES sink test")
  }



}
