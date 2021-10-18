package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /*
        1.根据课程ID查询课程的章节与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
        2.根据课程id回显章节对应的课程信息
     */
    public Course findCourseById(Integer courseId);

    /*
        3.新增章节信息
     */
    public void saveSection(CourseSection section);

    /*
        3.1更新章节信息
     */
    void updateSection(CourseSection section);

    /*
        4 修改章节状态
     */
    public void updateSectionStatus(Integer id,Integer status);
}
