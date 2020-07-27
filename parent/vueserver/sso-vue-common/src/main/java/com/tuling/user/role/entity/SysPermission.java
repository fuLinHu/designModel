package com.tuling.user.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tuling.page.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:资源表
* @author: smlz
* @createDate: 2019/12/20 14:02
* @version: 1.0
*/
@Data
@TableName("sys_permission")
public class SysPermission extends PageRequest {


    @TableId
    private Integer id;

    private Integer pid;

    private Integer type;

    private String name;

    private String code;

    private String uri;

    private Integer seq = 1;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    @TableField(exist = false)
    private List<SysPermission> children = new ArrayList<>();

}
