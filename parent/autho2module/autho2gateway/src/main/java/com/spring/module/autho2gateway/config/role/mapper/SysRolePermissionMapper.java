package com.spring.module.autho2.config.role.mapper;


import com.spring.module.autho2.config.role.entity.SysRolePermission;

import java.util.List;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public interface SysRolePermissionMapper {

    /**
     * 方法实现说明:根据角色ID查询资源列表
     * @author:smlz
     * @param roleIds
     * @return:List<SysRolePermission>
     * @exception:
     * @date:2019/12/20 15:14
     */
    List<SysRolePermission> findByRoleIds(List<Integer> roleIds);
}
