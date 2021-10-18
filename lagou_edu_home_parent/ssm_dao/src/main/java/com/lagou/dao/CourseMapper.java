package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    // 根据条件查询
    public List<Course> findCourseByCondition(CourseVo courseVo);

    //保存课程信息
    public void saveCourse(Course course);

    //保存讲师信息
    public void saveTeacher(Teacher teacher);

    //3.课程信息的回显(根据id查询课程信息及讲师信息)
    public CourseVo findCourseById(Integer id);

    //4.1 修改课程信息
    public void updateCourse(Course course);

    //4.2修改讲师信息
    public void updateTeacher(Teacher teacher);

    //5 修改课程状态信息
    public void updateCourseStatus(Course course);
}
