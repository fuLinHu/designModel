package com.tuling.config.role.service;


import com.tuling.config.role.entity.SysUser;
import com.tuling.page.PageRequest;
import com.tuling.page.PageResult;

import java.util.List;

/**
 * Created by smlz on 2019/12/20.
 */
public interface ISysUserService {

    SysUser getByUsername(String username);

}
