package com.gpfei.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gpfei.commonutils.R;
import com.gpfei.eduservice.entity.EduCourse;
import com.gpfei.eduservice.entity.EduTeacher;
import com.gpfei.eduservice.service.EduCourseService;
import com.gpfei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询4条名师
    @GetMapping("index")
    public R index(){

        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> eduCourses = courseService.list(courseQueryWrapper);

        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("id");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> eduTeachers = teacherService.list(teacherQueryWrapper);
        return R.ok().data("eduList",eduCourses).data("teacherList",eduTeachers);
    }
}
