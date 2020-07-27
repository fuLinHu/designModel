package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.dao.PermissionDao;
import com.spring.tiger.vue.vueuserserver.dao.RolePermissionDao;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRolePermission;
import com.tuling.user.role.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 16:27
 * @Version V1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public Page<SysPermission> findPageBy(SysPermission param) {
        Page<SysPermission> page = new Page<SysPermission>(param.getPageNum(), param.getPageSize());
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        Page<SysPermission> pageresult = (Page<SysPermission>) permissionDao.selectPage(page, queryWrapper);
        return pageresult;
    }

    @Override
    public List<SysPermission> findPermissionByRoleIds(List<Integer> roleIs){
        List<SysRolePermission> sysRolePermissionByRoleIds = findSysRolePermissionByRoleIds(roleIs);
        Set<Integer> permissionIds = new HashSet<>();
        sysRolePermissionByRoleIds.stream().forEach(o->{
            permissionIds.add(o.getPermissionId());
        });
        List<SysPermission> sysPermissions = permissionDao.selectBatchIds(permissionIds);
        for (SysPermission sysPermission : sysPermissions) {
            sysPermission.getId();
        }
        return sysPermissions;
    }

    @Override
    public List<SysRolePermission> findSysRolePermissionByRoleIds(List<Integer> roleIs){
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("roleId", roleIs);
        List<SysRolePermission> sysPermissions = rolePermissionDao.selectList(queryWrapper);
        return sysPermissions;
    }


}
