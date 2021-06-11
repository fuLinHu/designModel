package com.forest.tiger.rtemplateroot;

import com.forest.tiger.rtemplateroot.downloader.MyHttpClientDownloader;
import com.forest.tiger.rtemplateroot.pageprocessor.MyPageProcessor;
import com.forest.tiger.rtemplateroot.pipeline.MyPipeline;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import javax.management.JMException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/5/24 16:36
 * @Version V1.0
 */
public class RunCrawler {

    public static void main(String[] args) throws JMException {
        Spider thread = Spider.create(new MyPageProcessor())
                .addPipeline(new JsonFilePipeline("F:\\"))  //添加一个Pipeline，一个Spider可以有多个Pipeline spider.addPipeline(new ConsolePipeline()).addPipeline(new FilePipeline())
                .addUrl("http://www.java1234.com/")
                .setDownloader(new MyHttpClientDownloader())
                .setScheduler(new QueueScheduler()) //设置Scheduler，一个Spider只能有个一个Scheduler
                //.setDownloader(new PhantomJSDownloader())  //spider.setDownloader(new SeleniumDownloader()) 定制开发
                .thread(5);

        SpiderMonitor.instance().register(thread);
        thread.start();
    }
}
