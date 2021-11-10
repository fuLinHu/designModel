package com.forest.tiger.rtemplateroot.service.impl;

import com.forest.tiger.rtemplateroot.dao.StudentDao;
import com.forest.tiger.rtemplateroot.entity.Student;
import com.forest.tiger.rtemplateroot.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/8/10 14:33
 * @Param $
 * @return $
 * @Version V1.0
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentDao studentDao;

    @Override
    public int save(Student student){
        int insert = studentDao.insert(student);
        this.delete(1);
        System.out.println(1/0);
        return insert;
    }


    @Override
    public void delete(Integer id){
        studentDao.deleteById(id);
    }

    @Override
    public void run(){


    }
}
