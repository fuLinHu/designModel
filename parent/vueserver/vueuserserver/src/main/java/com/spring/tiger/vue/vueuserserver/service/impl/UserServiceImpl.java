package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.dao.UserDao;
import com.spring.tiger.vue.vueuserserver.service.UserService;
import com.tuling.user.role.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/24 19:49
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public Page<SysUser> findPageBy(SysUser sysUser){
        Page<SysUser> page = new Page<SysUser>(sysUser.getPageNum(), sysUser.getPageSize());
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNoneEmpty(sysUser.getUsername())){
            queryWrapper.like("username",sysUser.getUsername());
        }
        Page<SysUser> sysUserIPage = (Page<SysUser>) userDao.selectPage(page, queryWrapper);
        return sysUserIPage;
    }

    @Override
    public void editUserStatus(Integer id, Integer status) {
        SysUser sysUser = new SysUser();
        sysUser.setStatus(status);
        sysUser.setId(id);
        userDao.updateById(sysUser);
    }

    @Override
    public void addUser(SysUser param) {
        param.setStatus(1);
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        userDao.insert(param);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public void edit(SysUser sysUser) {
        userDao.updateById(sysUser);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }
}
