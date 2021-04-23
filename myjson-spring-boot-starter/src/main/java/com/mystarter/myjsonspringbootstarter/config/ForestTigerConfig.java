package com.mystarter.myjsonspringbootstarter.config;

import com.mystarter.myjsonspringbootstarter.service.ForestTiger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/3/25 16:21
 * @Version V1.0
 */
@Component
public class ForestTigerConfig {

    @Resource
    private List<ForestTiger> forestTigers;

    @PostConstruct
    public void run(){
        System.out.println(forestTigers.size());
    }
}
