package com.my.zookeeper.zkclient.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/12 23:14
 * @Version V1.0
 */
public class DubboClient {

    UserService service;
    // URL 远程服务的调用地址
    public UserService buildService(String url) {
        ApplicationConfig config = new ApplicationConfig("young-app");
        // 构建一个引用对象
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(config);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setUrl(url);
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://192.168.102.133:2181"));
        referenceConfig.setTimeout(5000);
        // 透明化
        this.service = referenceConfig.get();
        return service;
    }
    public static void main(String[] args) {
        DubboClient dubboClient = new DubboClient();
        UserService userService = dubboClient.buildService("");
        System.out.println(userService.toString());

    }
}
