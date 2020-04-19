package tes.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TeacherController {
    //根据类型找
   @Autowired
    private TeacherService teacherService;
    //根据名字找
   @Resource
    private TeacherService t1;
    /*@Inject
    private TeacherService t2;*/



}
