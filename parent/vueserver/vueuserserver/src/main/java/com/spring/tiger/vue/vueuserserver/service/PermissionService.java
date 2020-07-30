package com.spring.tiger.vue.vueuserserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRolePermission;
import com.tuling.user.role.entity.SysUser;

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

    List<SysPermission> selectAll();

    List<SysPermission> gengerAllPermissionTree();

    List<SysPermission> findPermissionByRoleIds(List<Integer> roleIs);

    List<SysRolePermission> findSysRolePermissionByRoleIds(List<Integer> roleIs);

    List<SysRolePermission> findSysRolePermissionByRoleId(Integer roleId);

    List<SysPermission> findPermissionByRoleId(Integer roleId);

    List<SysPermission> genertTree(List<SysPermission> sysPermissions);

    void deletePermissionByRole(Integer roleId, Integer permissionId);

    void deleteRolePermissionByRoleId(Integer roleId);

    void saveRolePermissionList(List<SysRolePermission> list);

    void deleteById(Integer id);

    void add(SysPermission param);

    void edit(SysPermission param);

    SysPermission findById(Integer id);
}
