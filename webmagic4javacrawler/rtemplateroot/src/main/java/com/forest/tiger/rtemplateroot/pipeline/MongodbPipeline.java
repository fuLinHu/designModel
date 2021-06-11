package com.forest.tiger.rtemplateroot.pipeline;

import com.forest.tiger.rtemplateroot.dataout.Dao.BaseDao;
import com.forest.tiger.rtemplateroot.pipeline.database.DatabasePipeLine;
import us.codecraft.webmagic.ResultItems;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/5/25 17:10
 * @Version V1.0
 */
public class MongodbPipeline extends DatabasePipeLine {

    public MongodbPipeline(BaseDao baseDao) {
        super(baseDao);
    }

}
