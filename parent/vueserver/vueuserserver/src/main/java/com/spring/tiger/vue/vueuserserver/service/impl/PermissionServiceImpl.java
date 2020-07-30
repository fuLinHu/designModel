package com.spring.tiger.vue.vueuserserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.tiger.vue.vueuserserver.dao.PermissionDao;
import com.spring.tiger.vue.vueuserserver.dao.RolePermissionDao;
import com.spring.tiger.vue.vueuserserver.service.PermissionService;
import com.tuling.user.role.entity.SysPermission;
import com.tuling.user.role.entity.SysRolePermission;
import com.tuling.user.role.entity.SysUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/26 16:27
 * @Version V1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;
    @Resource
    private RolePermissionDao rolePermissionDao;


    @Override
    public Page<SysPermission> findPageBy(SysPermission param) {
        Page<SysPermission> page = new Page<SysPermission>(param.getPageNum(), param.getPageSize());
        QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        Page<SysPermission> pageresult = (Page<SysPermission>) permissionDao.selectPage(page, queryWrapper);
        return pageresult;
    }

    @Override
    public List<SysPermission> selectAll(){
       return  permissionDao.selectAll();
    }

    @Override
    public List<SysPermission> gengerAllPermissionTree(){
        List<SysPermission> sysPermissions = this.selectAll();
        List<SysPermission> sysPermissionsTree = genertTree(sysPermissions);
        return sysPermissionsTree;
    }

    @Override
    public List<SysPermission> findPermissionByRoleIds(List<Integer> roleIs){
        List<SysRolePermission> sysRolePermissionByRoleIds = findSysRolePermissionByRoleIds(roleIs);
        Set<Integer> permissionIds = new HashSet<>();
        sysRolePermissionByRoleIds.stream().forEach(o->{
            permissionIds.add(o.getPermissionId());
        });
        List<SysPermission> sysPermissions = permissionDao.selectBatchIds(permissionIds);
        return sysPermissions;
    }

    @Override
    public List<SysRolePermission> findSysRolePermissionByRoleIds(List<Integer> roleIs){
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIs);
        List<SysRolePermission> sysPermissions = rolePermissionDao.selectList(queryWrapper);
        return sysPermissions;
    }

    @Override
    public List<SysRolePermission> findSysRolePermissionByRoleId(Integer roleId){
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<SysRolePermission> sysPermissions = rolePermissionDao.selectList(queryWrapper);
        return sysPermissions;
    }

    @Override
    public List<SysPermission> findPermissionByRoleId(Integer roleId){
        List<SysRolePermission> sysRolePermissionByRoleId = findSysRolePermissionByRoleId(roleId);
        Set<Integer> permissionIds = new HashSet<>();
        sysRolePermissionByRoleId.stream().forEach(o->{
            permissionIds.add(o.getPermissionId());
        });
        List<SysPermission> sysPermissions = new ArrayList<>();
        if(permissionIds!=null&&permissionIds.size()>0){
            sysPermissions = permissionDao.selectBatchIds(permissionIds);
        }
        return sysPermissions;
    }


    @Override
    public  List<SysPermission> genertTree(List<SysPermission> sysPermissions){
        Map<Integer,SysPermission> exitMap = new HashMap<>();
        for (SysPermission sysPermission : sysPermissions) {
            exitMap.put(sysPermission.getId(),sysPermission);
        }
        for (SysPermission sysPermission : sysPermissions) {
            Integer id = sysPermission.getId();
            for (SysPermission permission : sysPermissions) {
                Integer pid1 = permission.getPid();
                Integer id1 = permission.getId();
                if(pid1 == id){
                    exitMap.remove(id1);
                    sysPermission.getChildren().add(permission);
                }
            }
        }
        List<SysPermission> collect = exitMap.values().stream().collect(Collectors.toList());
        JSONArray jsonArray = JSONArray.fromObject(collect);
        System.out.println(jsonArray.toString());
        return collect;
    }

    private  void findSysPermissionSonByPermissionId(List<SysPermission> sysPermissions,Integer permissionId,List<SysPermission> result){
        List<Integer> son = new ArrayList<>();
        sysPermissions.forEach(o->{
            Integer pid = o.getPid();
            Integer id = o.getId();
            if(pid == permissionId){
                result.add(o);
                son.add(id);
            }
        });
        son.forEach(o->{
            findSysPermissionSonByPermissionId(sysPermissions,o,result);
        });
    }

    private  static  void findSysPermissionSonByPermissionId(List<SysPermission> sysPermissions,Integer permissionId,List<SysPermission> result,String id1){
        List<Integer> son = new ArrayList<>();
        sysPermissions.forEach(o->{
            Integer pid = o.getPid();
            Integer id = o.getId();
            if(pid == permissionId){
                result.add(o);
                son.add(id);
            }
        });
        son.forEach(o->{
            findSysPermissionSonByPermissionId(sysPermissions,o,result,"");
        });
    }


    @Override
    public void deletePermissionByRole(Integer roleId, Integer permissionId) {
        List<SysRolePermission> sysRolePermissionByRoleId = findSysRolePermissionByRoleId(roleId);
        List<Integer> permissionIds = new ArrayList<>();
        sysRolePermissionByRoleId.forEach(o->{
            permissionIds.add(o.getPermissionId());
        });
        List<SysPermission> sysPermissions = permissionDao.selectBatchIds(permissionIds);

        List<SysPermission> son = new ArrayList<>();
        findSysPermissionSonByPermissionId(sysPermissions,permissionId,son);
        SysPermission sysPermission = permissionDao.selectById(permissionId);
        son.add(sysPermission);

        List<Integer> permissionIdList = new ArrayList<>();
        son.forEach(o->{
            permissionIdList.add(o.getId());
        });


        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("permission_id",permissionIdList);
        rolePermissionDao.delete(queryWrapper);
    }

    @Override
    public void deleteRolePermissionByRoleId(Integer roleId){
        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id",roleId);
        rolePermissionDao.delete(queryWrapper);
    }

    @Override
    public void saveRolePermissionList(List<SysRolePermission> list){
        list.forEach(o->{
            rolePermissionDao.insert(o);
        });
    }

    @Override
    public void deleteById(Integer id) {
        rolePermissionDao.deleteById(id);
    }

    @Override
    public void add(SysPermission param) {
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        permissionDao.insert(param);
    }

    @Override
    public void edit(SysPermission param) {
        param.setCreateTime(new Date());
        param.setUpdateTime(new Date());
        permissionDao.updateById(param);
    }

    @Override
    public SysPermission findById(Integer id) {
        return permissionDao.selectById(id);
    }


    public static List<SysPermission> genertTree(List<SysPermission> sysPermissions,String str){
        Map<Integer,SysPermission> exitMap = new HashMap<>();
        for (SysPermission sysPermission : sysPermissions) {
            exitMap.put(sysPermission.getId(),sysPermission);
        }
        for (SysPermission sysPermission : sysPermissions) {
            Integer id = sysPermission.getId();
            for (SysPermission permission : sysPermissions) {
                Integer pid1 = permission.getPid();
                Integer id1 = permission.getId();
                if(pid1 == id){
                    exitMap.remove(id1);
                    sysPermission.getChildren().add(permission);
                }
            }
        }
        return exitMap.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<SysPermission> parent = new ArrayList<>();
        SysPermission sysPermission10 = new SysPermission();
        sysPermission10.setId(1);
        sysPermission10.setPid(0);
        sysPermission10.setName("leve10");
        parent.add(sysPermission10);

        SysPermission sysPermission30 = new SysPermission();
        sysPermission30.setId(6);
        sysPermission30.setPid(3);
        sysPermission30.setName("leve31");
        parent.add(sysPermission30);

        SysPermission sysPermission31 = new SysPermission();
        sysPermission31.setId(7);
        sysPermission31.setPid(3);
        sysPermission31.setName("leve32");
        parent.add(sysPermission31);

        SysPermission sysPermission40 = new SysPermission();
        sysPermission40.setId(8);
        sysPermission40.setPid(7);
        sysPermission40.setName("leve41");
        parent.add(sysPermission40);

        SysPermission sysPermission50 = new SysPermission();
        sysPermission50.setId(9);
        sysPermission50.setPid(8);
        sysPermission50.setName("leve51");
        parent.add(sysPermission50);

        SysPermission sysPermission11 = new SysPermission();
        sysPermission11.setId(2);
        sysPermission11.setPid(0);
        sysPermission11.setName("leve11");
        parent.add(sysPermission11);

        SysPermission sysPermission20 = new SysPermission();
        sysPermission20.setId(3);
        sysPermission20.setPid(1);
        sysPermission20.setName("leve20");
        parent.add(sysPermission20);

        SysPermission sysPermission21 = new SysPermission();
        sysPermission21.setId(4);
        sysPermission21.setPid(1);
        sysPermission21.setName("leve21");
        parent.add(sysPermission21);

        SysPermission sysPermission22 = new SysPermission();
        sysPermission22.setId(5);
        sysPermission22.setPid(2);
        sysPermission22.setName("leve22");
        parent.add(sysPermission22);

        List<SysPermission> result = new ArrayList<>();
        findSysPermissionSonByPermissionId(parent,0,result,"");

        List<SysPermission> sysPermissions = genertTree(result, "");
        JSONArray jsonArray = JSONArray.fromObject(sysPermissions);
        System.out.println(jsonArray.toString());
        //List<SysPermission> sysPermissions = genertTree(parent);
//        JSONArray jsonArray = JSONArray.fromObject(sysPermissions);
//        System.out.println(jsonArray.toString());
    }


}
