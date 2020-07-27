package com.spring.tiger.vue.vueuserserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.user.role.entity.SysRole;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 17:32
 * @Version V1.0
 */
public interface RoleService {
    Page<SysRole> findPageBy(SysRole param);
}
