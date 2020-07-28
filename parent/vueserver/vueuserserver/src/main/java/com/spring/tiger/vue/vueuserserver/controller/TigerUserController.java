package com.spring.tiger.vue.vueuserserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.service.UserService;
import com.tuling.page.PageRequest;
import com.tuling.user.role.entity.SysUser;
import com.tuling.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/11 22:37
 * @Version V1.0
 */
@RestController
@RequestMapping("/tigerUser")
public class TigerUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/page")
    public Result<Page<SysUser>> findPageBy(SysUser param) {
        Page<SysUser> pageBy = userService.findPageBy(param);
        return Result.success(pageBy);
    }

    @RequestMapping("/edit/{id}/{status}")
    public Result<?> editUserStatus(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        try {
            userService.editUserStatus(id, status);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    @RequestMapping("/edit")
    public Result<?> edit(SysUser param) {
        try {
            userService.edit(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    @RequestMapping("/save")
    public Result<?>addUser(SysUser param) {
        try {
            userService.addUser(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    @RequestMapping("/findById/{id}")
    public Result<SysUser> findById(@PathVariable("id") Integer id) {
        try {
            SysUser sysUser = userService.findById(id);
            return Result.success(sysUser);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
    @RequestMapping("/deleteById/{id}")
    public Result<SysUser> deleteById(@PathVariable("id") Integer id) {
        try {
            userService.deleteById(id);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }

    }
}
