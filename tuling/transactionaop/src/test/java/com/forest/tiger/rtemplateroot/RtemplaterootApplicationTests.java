package com.forest.tiger.rtemplateroot;


import com.forest.tiger.rtemplateroot.entity.Student;
import com.forest.tiger.rtemplateroot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RtemplaterootApplicationTests {

    @Resource
    private StudentService studentService;

    @Test
    void contextLoads() {
        Student student = new Student();
        student.setName("7777711");
        studentService.save(student);

    }

}
