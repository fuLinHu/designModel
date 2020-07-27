package com.spring.tiger.vue.vueuserserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.service.RoleService;
import com.tuling.user.role.entity.SysRole;
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
@RequestMapping("/tigerRole")
public class TigerRoleController {

    @Resource
    private RoleService roeService;

    @RequestMapping("/page")
    public Result<Page<SysRole>> findPageBy(SysRole param) {
        Page<SysRole> pageBy = roeService.findPageBy(param);
        return Result.success(pageBy);
    }

}
