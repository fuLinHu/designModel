package com.spring.tiger.vue.vueuserserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRolePermission;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 16:27
 * @Version V1.0
 */
public interface PermissionService {
    Page<SysPermission> findPageBy(SysPermission param);

    List<SysPermission> findPermissionByRoleIds(List<Integer> roleIs);

    List<SysRolePermission> findSysRolePermissionByRoleIds(List<Integer> roleIs);
}
