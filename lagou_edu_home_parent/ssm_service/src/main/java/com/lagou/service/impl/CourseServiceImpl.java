package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> findCourseByCondition(CourseVo courseVo) {

        List<Course> list = courseMapper.findCourseByCondition(courseVo);

        return list;
    }

    // 保存课程及讲师信息
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();

        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        // 保存课程
        courseMapper.saveCourse(course);

        //获取新插入课程数据的id
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师
        courseMapper.saveTeacher(teacher);
    }


    //3.根据id查询课程
    public CourseVo findCourseById(Integer id) {

        return courseMapper.findCourseById(id);
    }

    // 4更新课程及讲师信息
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date = new Date();
        course.setUpdateTime(date);

        //更新课程信息
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());

        //更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    // 5 更改课程状态
    @Override
    public void updateCourseStatus(int courseId, int status) {

        //封装数据
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //调用mapper
        courseMapper.updateCourseStatus(course);

    }
}
