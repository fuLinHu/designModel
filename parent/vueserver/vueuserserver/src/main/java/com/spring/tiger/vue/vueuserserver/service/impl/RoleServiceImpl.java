package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.dao.PermissionDao;
import com.spring.tiger.vue.vueuserserver.dao.RoleDao;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.spring.tiger.vue.vueuserserver.service.RoleService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRole;
import com.tuling.user.role.entity.SysRolePermission;
import com.tuling.user.role.entity.SysUser;
import com.tuling.util.HanYuPinYinUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 17:32
 * @Version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao dao;
    @Resource
    private PermissionService permissionService;
    @Override
    public Page<SysRole> findPageBy(SysRole param) {
        Page<SysRole> page = new Page<SysRole>(param.getPageNum(), param.getPageSize());
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        Page<SysRole> pageresult = (Page<SysRole>) dao.selectPage(page, queryWrapper);
        List<SysRole> records = pageresult.getRecords();
        putPermissionToRole(records);
        return pageresult;
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public SysRole findById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public void add(SysRole param) {
        param.setRoleCode(HanYuPinYinUtil.getPingYin(param.getRoleName()));
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        dao.insert(param);
    }

    @Override
    public void edit(SysRole pram) {
        dao.updateById(pram);
    }

    @Override
    public List<SysPermission> deletePermissionByRole(Integer roleId, Integer permissionId) {
        permissionService.deletePermissionByRole(roleId,permissionId);
        List<SysPermission> permissionByRoleId = permissionService.findPermissionByRoleId(roleId);
        List<SysPermission> sysPermissions = permissionService.genertTree(permissionByRoleId);
        return sysPermissions;
    }

    @Override
    public List<SysPermission> getAllPermissionTree() {
        return permissionService.gengerAllPermissionTree();
    }

    @Override
    public void allocatePermission(Integer roleId, String permissionIds) {
        permissionService.deleteRolePermissionByRoleId(roleId);
        if(permissionIds!=null||"".equals(permissionIds)){
            String[] permissionIdArr = permissionIds.split(",");
            List<SysRolePermission> list = new ArrayList<>();

            Arrays.stream(permissionIdArr).filter(o->!"".equals(o)&&o!=null).forEach(o->{
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setPermissionId(Integer.parseInt(o));
                sysRolePermission.setRoleId(roleId);
                list.add(sysRolePermission);
            });
            permissionService.saveRolePermissionList(list);
        }
    }


    private void  putPermissionToRole(List<SysRole> records){
        records.stream().forEach(o->{
            Integer id = o.getId();
            List<SysPermission> permissionByRoleId = permissionService.findPermissionByRoleId(id);
            List<SysPermission> sysPermissions = permissionService.genertTree(permissionByRoleId);
            o.setChildren(sysPermissions);
        });
    }

    public static void main(String[] args) {
        String kk = HanYuPinYinUtil.getPingYin("你好");
        System.out.println(kk);
    }


}
