package com.forest.tiger.rtemplateroot.pipeline.database;

import com.forest.tiger.rtemplateroot.dataout.Dao.BaseDao;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/5/25 19:46
 * @Version V1.0
 */
public  class DatabasePipeLine implements Pipeline{
    private BaseDao baseDao;
    public DatabasePipeLine(BaseDao baseDao){
        this.baseDao = baseDao;
    }
    public BaseDao getBaseDao() {
        return baseDao;
    }

    @Override
    public  void process(ResultItems resultItems, Task task){
        baseDao.insert(resultItems);
    }
}
