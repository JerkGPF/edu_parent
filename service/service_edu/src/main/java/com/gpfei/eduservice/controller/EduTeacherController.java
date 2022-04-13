package com.gpfei.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpfei.commonutils.R;
import com.gpfei.eduservice.entity.EduTeacher;
import com.gpfei.eduservice.entity.vo.TeacherQuery;
import com.gpfei.eduservice.service.EduTeacherService;
import com.gpfei.servicebase.exceptionhandler.MyExpectation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author gpfei
 * @since 2022-04-11
 */
@Api(tags="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;
    //1.查询讲师所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2.逻辑删除讲师
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag)
            return R.ok();
        else
            return R.error();
    }
    //3.分页查询讲师
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)  @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);


//        try{
//            int i = 10/0;
//        }catch (Exception e){
//            throw new MyExpectation(20001,"执行了自定义异常处理。。。");
//        }



        //调用方法实现分页,底层封装，把所有数据封装到pageTeacher对象中
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return R.ok().data("total",total).data("rows",records);

    }
    //4.条件查询带分页
    @ApiOperation(value = "带分页条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
                                  @ApiParam(name = "teacherQuery", value = "查询对象", required = false) @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //wrapper,多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空,为空不拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合
        return R.ok().data("total",total).data("rows",records);

    }
    //5.添加讲师接口的方法
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                            @RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if(save)
            return R.ok();
        else
            return R.error();
    }
    //6.根据讲师id进行查询
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                            @PathVariable String id){
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    //7.讲师修改功能
    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@ApiParam(name = "eduTeacher", value = "讲师对象id必须有", required = true)
                               @RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if(b)
            return R.ok();
        else
            return R.error();
    }
}

