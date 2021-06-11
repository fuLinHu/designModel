package com.forest.tiger.rtemplateroot.downloader;

import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/6/11 15:10
 * @Version V1.0
 */
public class MyHttpClientDownloader extends HttpClientDownloader {

    public MyHttpClientDownloader(){
        SimpleProxyProvider from = SimpleProxyProvider.from(
                new Proxy("119.252.168.52", 53281)
                , new Proxy("190.83.12.228", 999));
        this.setProxyProvider(from);
    }
}
