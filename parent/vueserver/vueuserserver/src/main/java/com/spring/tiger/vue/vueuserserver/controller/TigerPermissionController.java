package com.spring.tiger.vue.vueuserserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysUser;
import com.tuling.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 16:24
 * @Version V1.0
 */
@RestController
@RequestMapping("/tigerPermission")
public class TigerPermissionController {

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/page")
    public Result<Page<SysPermission>> findPageBy(SysPermission param) {
        Page<SysPermission> pageBy = permissionService.findPageBy(param);
        return Result.success(pageBy);
    }

}
