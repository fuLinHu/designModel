package com.spring.module.autho2.config.role.service;


import com.spring.module.autho2.config.role.entity.SysUser;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysUserService {

    SysUser getByUsername(String username);
}
