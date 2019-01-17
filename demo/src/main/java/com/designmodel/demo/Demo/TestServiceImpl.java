package com.designmodel.demo.Demo;

public class TestServiceImpl extends BaseService<Test> implements  TestService {
    @Override
    public void saveOrUpdate(Test t) {
        System.out.println("我是test子类的数据"+t);
    }


}
