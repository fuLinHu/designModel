package com.spring.module.autho2gateway.config.role.mapper;

import com.spring.module.autho2gateway.config.role.entity.SysUserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by smlz on 2019/12/20.
 */
public interface SysUserRoleMapper {

    /**
     * 方法实现说明:根据用户ID查询 用户角色列别
     *
     * @param userId
     * @author:smlz
     * @return: List<SysUserRole>
     * @exception:
     * @date:2019/12/20 14:52
     */

    @Select("select * from sys_user_role where user_id=#{userId}")
    List<SysUserRole> findByUserId(Integer userId);
}
