package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //1.分页查询广告信息
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllAdByPage(promotionAdVo);

        return new ResponseResult(true,200,"分页查询成功",pageInfo);
    }

    //2.广告图片上传
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.先判断获取到的文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        // 切割获取到的路径
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取源文件名称
        String originalFilename = file.getOriginalFilename();

        //4.设置新的名称 以防重复
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //5.1如果目录不存在就创建的新的目录
        if(!filePath.getParentFile().exists()){

            filePath.getParentFile().mkdir();
        }

        //5.2进行文件上传
        file.transferTo(filePath);

        //6.将文件的路径放回
        HashMap<String, Object> map = new HashMap<>();

        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "上传图片成功", map);

        return  responseResult;
    }


    /*
            2.修改广告上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){

        promotionAdService.updatePromotionAdStatus(id,status);

        return new ResponseResult(true,200,"修改上下线成功",status);
    }
}
