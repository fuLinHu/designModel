package com.spring.tiger.vue.vueuserserver.service;

import com.tuling.user.role.entity.SysUserRole;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/30 9:06
 * @Version V1.0
 */
public interface UserRoleService {
    void save(SysUserRole sysUserRole);
    List<SysUserRole> findBy(SysUserRole sysUserRole);

    void delete(Integer userId, Integer roleId);
}
