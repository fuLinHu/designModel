package com.spring.module.autho2gateway.config.role.service;


import com.spring.module.autho2gateway.config.role.entity.SysUser;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysUserService {

    SysUser getByUsername(String username);
}
