package com.my.web.tiger.service.impl;

import com.my.web.tiger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/6 8:49
 * @Version V1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public Object information() {
        String name = this.getClass().getName();
        log.info("我的类名为：："+name);
        return name;
    }
}
