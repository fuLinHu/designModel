package com.spring.tiger.vue.vueuserserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRole;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 17:32
 * @Version V1.0
 */
public interface RoleService {
    Page<SysRole> findPageBy(SysRole param);

    void deleteById(Integer id);

    SysRole findById(Integer id);

    void add(SysRole param);

    void edit(SysRole pram);

    List<SysPermission> deletePermissionByRole(Integer roleId, Integer permissionId);

    List<SysPermission> getAllPermissionTree();

    void allocatePermission(Integer roleId, String permissionIds);
}
