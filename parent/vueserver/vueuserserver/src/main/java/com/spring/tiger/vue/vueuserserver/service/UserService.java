package com.spring.tiger.vue.vueuserserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuling.user.role.entity.SysUser;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/24 19:49
 * @Version V1.0
 */
public interface UserService {
    Page<SysUser> findPageBy(SysUser sysUser);

    void editUserStatus(Integer id, Integer status);

    void addUser(SysUser sysUser);

    SysUser findById(Integer id);

    void editUser(SysUser sysUser);

    void deleteById(Integer id);
}
