package com.forest.tiger.rtemplateroot.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/5/25 8:55
 * @Version V1.0
 */
public class MyPipeline  implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> all = resultItems.getAll();
        if(all!=null){
            for (Map.Entry<String, Object> entry : all.entrySet()) {
                System.out.println(entry.getKey() + ":\t" + entry.getValue());
            }
        }
        
    }
}
