package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        List<CourseSection> sectionList = courseContentMapper.findSectionAndLessonByCourseId(courseId);

        return sectionList;
    }

    /*
        2根据课程id回显章节对应的课程信息
     */
    @Override
    public Course findCourseById(Integer courseId) {

        Course course = courseContentMapper.findCourseById(courseId);

        return course;
    }

    /*
         3.新增章节信息
    */
    @Override
    public void saveSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        courseContentMapper.saveSection(section);
    }

    /*
           3.1更新章节信息
     */
    @Override
    public void updateSection(CourseSection section) {

        //补全信息
        section.setUpdateTime(new Date());

        courseContentMapper.updateSection(section);
    }

    /*
        4 修改章节状态
     */
    @Override
    public void updateSectionStatus(Integer id, Integer status) {

        CourseSection section = new CourseSection();

        section.setStatus(status);
        section.setId(id);
        section.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(section);
    }


}
