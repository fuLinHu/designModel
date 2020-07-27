package com.tuling.user.role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.tuling.page.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 * Created by smlz on 2019/12/20.
 */
@Data
@TableName("sys_user")
public class SysUser extends PageRequest {

    @TableId
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private Integer status = 0;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

}
