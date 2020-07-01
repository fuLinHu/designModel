package com.spring.module.autho2gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/22 9:22
 * @Version V1.0
 */

/**
 * @Author 付林虎
 * @Description //TODO  授权中心安全配置
 * @Date 2020/6/22 9:22
 * @Param
 * @Version V1.0
 * @return
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyTigerUserDetailService userDetailsService;

    /**
     * @return void
     * @Author 付林虎
     * @Description //TODO 用于构建用户认证组件(AuthenticationManagerBuilder,这里典型的是一个建造者模式), 需要传递userDetailsService和密码加密器
     * @Date 2020/6/22 9:24
     * @Param [auth]
     * @Version V1.0
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        //这里的userDetailService接口是spring security的 我们需要自己
        //写接口来实现，我们在MyTigerUserDetailService中 查询数据库信息来进行获取用户信息
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    /**
     * 密码加密器组件
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 上面的configure真正的构建好了我们的AuthenticationManagerBuilder 我们在这里
     * 需要通过建造者 构建我们的AuthenticationManager对象
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
