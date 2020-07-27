package com.tuling.controller;

import com.tuling.entity.menu.Menu;
import com.tuling.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/18 10:53
 * @Version V1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("/getMenuList")
    public Result<List<Menu>> getMenuList(){
        List<Menu> parnet = new ArrayList<>();
        Menu menu = new Menu();
        menu.setId(1);
        menu.setMenuName("用户管理");

        Menu menu1 = new Menu();
        menu1.setId(2);
        menu1.setMenuName("权限管理");
        Menu menu2 = new Menu();

        menu2.setId(3);
        menu2.setMenuName("商品管理");
        Menu menu3 = new Menu();
        menu3.setId(4);
        menu3.setMenuName("订单管理");
        Menu menu4 = new Menu();
        menu4.setId(5);
        menu4.setMenuName("数据统计");

        List<Menu> childrentList = new ArrayList<>();
        menu.setChildrent(childrentList);
        Menu childrent = new Menu();
        childrent.setId(11);
        childrent.setMenuName("用户列表");
        childrent.setPath("/user");
        childrentList.add(childrent);

        List<Menu> childrentList21 = new ArrayList<>();
        menu1.setChildrent(childrentList21);
        Menu childrent21 = new Menu();
        childrent21.setId(21);
        childrent21.setMenuName("角色列表");
        childrent21.setPath("/role");
        childrentList21.add(childrent21);

        Menu childrent22 = new Menu();
        childrent22.setId(22);
        childrent22.setMenuName("权限列表");
        childrent22.setPath("/permission");
        childrentList21.add(childrent22);


        parnet.add(menu);
        parnet.add(menu1);
        parnet.add(menu2);
        parnet.add(menu3);
        parnet.add(menu4);
        return  Result.success(parnet);
    }

}
