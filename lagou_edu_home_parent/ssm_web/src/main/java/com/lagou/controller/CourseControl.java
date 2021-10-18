package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseControl {

    @Autowired
    private CourseService courseService;

    /*
      根据条件查询课程列表
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        List<Course> list = courseService.findCourseByCondition(courseVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

       //  System.out.println(list);

        return responseResult;

    }

    /*
       课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // 对路径切割获取 // D:\apache-tomcat-8.5.56\webapps\
        String suString = realPath.substring(0,realPath.indexOf("ssm_web"));

        //3.获取原文件名称
        String originalFilename = file.getOriginalFilename();

        //4.设置新的文件名称 防止重复
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        //创建新文件夹  D:\apache-tomcat-8.5.56\webapps\pload
        String uploadPath = suString + "upload\\";
        File filePath = new File(uploadPath,newFileName);

        //如果目录不存在就创建新的目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建的目录是：" + filePath);
        }

        // 进行图片的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        // filePath:"http://localhost:8080/upload/1597112871741.JPG"  在webapps目录下的upload
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true,200,"图片上传成功",map);

        return responseResult;
    }

    /*
        新增课程信息及讲师信息
        新增课程信息与修改课程信息写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {

        ResponseResult responseResult = null;

        if (courseVo.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVo);

            responseResult = new ResponseResult(true, 200, "添加成功", null);
        }else {
            courseService.updateCourseOrTeacher(courseVo);

            responseResult = new ResponseResult(true, 200, "修改成功", null);
        }
        return responseResult;
    }

    /*
       根据id查询课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVo courseVo = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVo);

        return responseResult;
    }

    /*
        课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){

        //调用service
        courseService.updateCourseStatus(id,status);

        //数据响应
        HashMap<String, Object> map = new HashMap<>();

        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;
    }

}
