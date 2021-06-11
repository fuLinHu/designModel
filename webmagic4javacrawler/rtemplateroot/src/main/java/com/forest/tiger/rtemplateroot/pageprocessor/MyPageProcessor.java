package com.forest.tiger.rtemplateroot.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/5/24 16:02
 * @Version V1.0
 */
public class MyPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        Selectable aList = page.getHtml().xpath("//div[@class='bignews']/ul/li/a");
        //Selectable xpath = page.getHtml().xpath("//div[@class='bignews']/ul/li/a/text()");
        List<String> links = aList.links().all();
        if(links!=null&&links.size()>0){
            page.addTargetRequests(links);
        }
        page.putField("title", page.getHtml().xpath("//div[@class='viewbox']/div/h2/text()"));
        page.putField("content", page.getHtml().xpath("//div[@class='content']/table"));



    }

    @Override
    public Site getSite() {
        return site;
    }
}
