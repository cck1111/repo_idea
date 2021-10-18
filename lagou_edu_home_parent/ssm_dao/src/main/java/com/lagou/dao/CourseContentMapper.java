package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /*
           根据课程id查询课程的章节与课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
      根据课程id回显回显章节对应的课程信息
     */
    public Course findCourseById(int courseId);

    /*
      3.新增章节信息
     */
    public void saveSection(CourseSection section);

    /*
        3.1修改章节信息
     */
    void updateSection(CourseSection section);

    /*
         4 修改章节状态
     */
    public void updateSectionStatus(CourseSection section);
}
