package com.spring.module.autho2.config;

import com.alibaba.fastjson.JSON;
import com.spring.module.autho2.config.role.domin.TulingUser;
import com.spring.module.autho2.config.role.entity.SysPermission;
import com.spring.module.autho2.config.role.entity.SysUser;
import com.spring.module.autho2.config.role.mapper.SysUserMapper;
import com.spring.module.autho2.config.role.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO 该类都是基于内存的 ，后期会改变为db,需要去数据库中查询
 * @Author 付林虎
 * @Date 2020/6/22 9:28
 * @Version V1.0
 */
@Slf4j
@Component("userDetailsService")
public class MyTigerUserDetailService implements UserDetailsService {
    //密码加密组件
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysPermissionService sysPermissionService;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        SysUser sysUser = sysUserMapper.findByUserName(userName);

        if(null == sysUser) {
            log.warn("根据用户名:{}查询用户信息为空",userName);
            throw new UsernameNotFoundException(userName);
        }

        List<SysPermission> sysPermissionList = sysPermissionService.findByUserId(sysUser.getId());

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysPermissionList)) {
            for (SysPermission sysPermission : sysPermissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getUri()));
            }
        }

        TulingUser tulingUser = new TulingUser(sysUser.getUsername(),passwordEncoder.encode(sysUser.getPassword()),authorityList);
        log.info("用户登陆成功:{}", JSON.toJSONString(tulingUser));
        return tulingUser;
    }
}
