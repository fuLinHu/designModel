package com.tuling.entity.menu;

import lombok.Data;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/18 10:55
 * @Version V1.0
 */
@Data
public class Menu {
    private int id;
    private String menuName;
    private String path;
    private String parentId;
    private List<Menu> childrent;
}
