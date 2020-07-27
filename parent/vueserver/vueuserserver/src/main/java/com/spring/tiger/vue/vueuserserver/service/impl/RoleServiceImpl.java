package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.dao.PermissionDao;
import com.spring.tiger.vue.vueuserserver.dao.RoleDao;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.spring.tiger.vue.vueuserserver.service.RoleService;
import com.tuling.user.role.entity.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 17:32
 * @Version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionService permissionService;
    @Override
    public Page<SysRole> findPageBy(SysRole param) {
        Page<SysRole> page = new Page<SysRole>(param.getPageNum(), param.getPageSize());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        Page<SysRole> pageresult = (Page<SysRole>) roleDao.selectPage(page, queryWrapper);


        return pageresult;
    }




}
