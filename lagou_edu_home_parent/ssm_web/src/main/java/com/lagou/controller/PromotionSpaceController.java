package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    //1.查询所有广告位
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告位成功", allPromotionSpace);

        return responseResult;
    }

    //2.添加广告位
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        if (promotionSpace.getId() == null){

            //新增
            promotionSpaceService.savePromotionSpace(promotionSpace);

            return new ResponseResult(true,200,"添加广告位成功",null);
        }else {
            // 修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"修改广告名称成功" ,null);
        }

    }

    //2.1根据id查询广告位信息-
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){

        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true,200,"响应成功",promotionSpaceById);
    }
}
