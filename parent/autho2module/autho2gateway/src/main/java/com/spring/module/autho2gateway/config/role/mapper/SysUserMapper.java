package com.spring.module.autho2gateway.config.role.mapper;


import com.spring.module.autho2gateway.config.role.entity.SysUser;

/**
 * Created by smlz on 2019/12/20.
 */
public interface SysUserMapper {

    SysUser findByUserName(String userName);
}
