package com.tuling.user.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tuling.page.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述 角色表
* @author: smlz
* @createDate: 2019/12/20 13:48
* @version: 1.0
*/
@Data
@TableName("sys_role")
public class SysRole  extends PageRequest {

    @TableId
    private Integer id;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    @TableField(exist = false)
    private List<SysPermission> children; //权限列表
}
