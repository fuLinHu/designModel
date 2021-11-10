package com.forest.tiger.rtemplateroot.service;

import com.forest.tiger.rtemplateroot.entity.Student;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/8/10 14:32
 * @Param $
 * @return $
 * @Version V1.0
 */
public interface StudentService {
    int save(Student student);

    void delete(Integer id);

    void run();
}
