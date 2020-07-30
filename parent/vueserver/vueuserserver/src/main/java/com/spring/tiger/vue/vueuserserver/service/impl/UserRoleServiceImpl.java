package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.tiger.vue.vueuserserver.dao.UserRoleDao;
import com.spring.tiger.vue.vueuserserver.service.UserRoleService;
import com.tuling.user.role.entity.SysUserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/30 9:06
 * @Version V1.0
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public void save(SysUserRole sysUserRole){
        userRoleDao.insert(sysUserRole);
    }

    @Override
    public List<SysUserRole> findBy(SysUserRole sysUserRole) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(sysUserRole.getUserId()+"")){
            queryWrapper.eq("user_id",sysUserRole.getUserId());
        }
        if(!StringUtils.isEmpty(sysUserRole.getRoleId()+"")){
            queryWrapper.eq("role_id",sysUserRole.getRoleId());
        }
        return userRoleDao.selectList(queryWrapper);
    }

    @Override
    public void delete(Integer userId,Integer roleId){
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("role_id",roleId);
        userRoleDao.delete(queryWrapper);
    }

}
