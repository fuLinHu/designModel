package com.spring.tiger.vue.vueuserserver.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tuling.user.role.entity.SysPermission;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 16:29
 * @Version V1.0
 */
public interface PermissionDao extends BaseMapper<SysPermission> {
    public List<SysPermission> selectAll();
}
