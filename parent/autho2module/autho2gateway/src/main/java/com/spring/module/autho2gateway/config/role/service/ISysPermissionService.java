package com.spring.module.autho2gateway.config.role.service;


import com.spring.module.autho2gateway.config.role.entity.SysPermission;

import java.util.List;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysPermissionService {

    List<SysPermission> findByUserId(Integer userId);
}
