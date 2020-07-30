package com.tuling.page;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/18 17:19
 * @Version V1.0
 */
@Data
public class PageRequest {
    /**
     * 当前页码
     */
    @TableField(exist = false)
    private int pageNum =1;
    /**
     * 每页数量
     */
    @TableField(exist = false)
    private int pageSize = 5;

}
