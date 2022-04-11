package com.gpfei.eduservice.controller;


import com.gpfei.eduservice.entity.EduTeacher;
import com.gpfei.eduservice.service.EduTeacherService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gpfei
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;
    //1.查询讲师所有数据
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //2.逻辑删除讲师
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

