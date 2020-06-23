package com.spring.module.autho2.config.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @className
 * @Description TODO  授权中心的配置
 * @Author 付林虎
 * @Date 2020/6/22 8:53
 * @Version V1.0
 */
@Configuration
@EnableAuthorizationServer
public class MyTigerAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    /**
     * @Author 付林虎
     * @Description //TODO  表示的资源服务器 校验token的时候需要干什么(这里表示需要带入自己appid,和app secret)来进行验证
     * @Date 2020/6/22 9:06
     * @Param [security]
     * @Version V1.0
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        //获取tokenkey需要登陆
        security.checkTokenAccess("isAuthenticated()");

    }


    /**
     * @Author 付林虎
     * @Description //TODO  作为授权服务器,必须知道有哪些第三方app 来向我们授权服务器 索取令牌,所以这个方法就是配置 哪些第三方app可以来获取我们的令牌的.
     * @Date 2020/6/22 8:58
     * @Param [clients]
     * @Version V1.0
     * @return void
     **/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
        clients.inMemory()
                    .withClient("portal_app")
                    .secret(passwordEncoder.encode("portal_app"))
                    .authorizedGrantTypes("password","authorization_code")//密码模式   授权码模式
                    .scopes("read") //权限  只读
                    .accessTokenValiditySeconds(3600)//token有限期
                    .resourceIds("order-service","product-service") //资源 id
                    .redirectUris("http://www.baidu.com") //获取授权后得跳转路径
                .and()
                    .withClient("order_app")
                    .secret(passwordEncoder.encode("smlz"))
                    .accessTokenValiditySeconds(1800)
                    .scopes("read")
                    .authorizedGrantTypes("password")
                    .resourceIds("order-service")
                .and()
                    .withClient("product_app")
                    .secret(passwordEncoder.encode("smlz"))
                    .accessTokenValiditySeconds(1800)
                    .scopes("read")
                    .authorizedGrantTypes("password")
                    .resourceIds("product-service");

    }

    /**
     * @Author 付林虎
     * @Description //TODO 方法实现说明:认证服务器 需要知道 是哪个用户来访问资源服务器，所以该方法是用来 验证用户信息的
     * @Date 2020/6/22 9:07
     * @Param [endpoints] 认证服务器的 识别用户信息的配置
     * @Version V1.0
     * @return void
     **/
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        //这这里，认证服务器委托一个AuthenticationManager 来验证我们的用户信息
        //这里的authenticationManager 怎么来的 ？
        endpoints.authenticationManager(authenticationManager);
    }
}
