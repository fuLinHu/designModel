package com.elk.demo;

import com.mystarter.myjsonspringbootstarter.MyjsonSpringBootStarterApplication;
import com.mystarter.myjsonspringbootstarter.service.MyJsonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes= MyjsonSpringBootStarterApplication.class)
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
    @Resource
    private MyJsonService myJsonService;

    @Test
    public void tesst(){
        String s = myJsonService.objToJson("我的品牌：");
        System.out.println(s);
    }


}
