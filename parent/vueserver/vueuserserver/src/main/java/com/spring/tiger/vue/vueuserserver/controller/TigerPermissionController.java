package com.spring.tiger.vue.vueuserserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRole;
import com.tuling.user.role.entity.SysUser;
import com.tuling.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/findById/{id}")
    public Result<SysPermission> findById(@PathVariable("id") Integer id) {
        try {
            SysPermission param = permissionService.findById(id);
            return Result.success(param);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @RequestMapping("/edit")
    public Result<?> edit(SysPermission param) {
        try {
            permissionService.edit(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @RequestMapping("/save")
    public Result<?>add(SysPermission param) {
        try {
            permissionService.add(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

    @RequestMapping("/deleteById/{id}")
    public Result<SysUser> deleteById(@PathVariable("id") Integer id) {
        try {
            permissionService.deleteById(id);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }

}
