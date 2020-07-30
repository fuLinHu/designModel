package com.spring.tiger.vue.vueuserserver.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.service.RoleService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRole;
import com.tuling.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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


    @RequestMapping("/deleteById/{id}")
    public Result<?> deleteById(@PathVariable("id") Integer id) {
        try {
            roeService.deleteById(id);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    @RequestMapping("/findById/{id}")
    public Result<SysRole> findById(@PathVariable("id") Integer id) {
        try {
            SysRole param = roeService.findById(id);
            return Result.success(param);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    @RequestMapping("/save")
    public Result<?>add(SysRole param) {
        try {
            roeService.add(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }

    @RequestMapping("/edit")
    public Result<?> edit(SysRole param) {
        try {
            roeService.edit(param);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    /**
     * @Author 付林虎
     * @Description //TODO  根据角色和权限  id 删除 改角色下的权限
     * @Date 2020/7/28 13:18
     * @Param [roleId, permissionId]
     * @Version V1.0
     * @return com.tuling.vo.Result<?>
     **/
    @RequestMapping("/deletePermissionByRole/{roleId}/{permissionId}")
    public Result<?> deletePermissionByRole(@PathVariable("roleId") Integer roleId,@PathVariable("permissionId") Integer permissionId) {
        try {
            List<SysPermission> sysPermissions = roeService.deletePermissionByRole(roleId, permissionId);
            return Result.success(sysPermissions);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }


    @RequestMapping("/getAllPermissionTree")
    public Result<List<SysPermission>> getAllPermissionTree() {
        try {
            List<SysPermission> param=roeService.getAllPermissionTree();
            return Result.success(param);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }
    @RequestMapping("/allocatePermission/{roleId}")
    public Result<?> allocatePermission(@PathVariable("roleId") Integer roleId, String permissionIds) {
        try {
            roeService.allocatePermission(roleId, permissionIds);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail();
        }
    }





}
