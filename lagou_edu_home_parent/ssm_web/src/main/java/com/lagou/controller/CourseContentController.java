package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){

        //调用service
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询章节及课时信息成功", sectionList);

        return responseResult;

    }

    // 2 根据课程id回显章节对应的课时信息
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam("courseId") Integer courseId){

        Course course = courseContentService.findCourseById(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);

        return responseResult;
    }

    //3.新增章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section){

        if(section.getId() == null){
            // 新增
            courseContentService.saveSection(section);

            return new ResponseResult(true,200,"新增成功",null);
        }else {
            //更新操作
            courseContentService.updateSection(section);

            return new ResponseResult(true,200,"更新成功",null);

        }
    }

    // 4 修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){

        courseContentService.updateSectionStatus(id,status);

        return new ResponseResult(true,200,"响应成功",status);
    }
}
