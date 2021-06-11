package com.luban.oauth2ssoclientdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fox
 */
@RestController
public class IndexController {
    
    @RequestMapping("/")
    public String index(){
        return "sso index ";
    }
}
