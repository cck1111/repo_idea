package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCgController {

    @Autowired
    private ResourceCgService resourceCgService;

    /*
        查询所有资源分类信息
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> categoryList = resourceCgService.findAllResourceCategory();

        return new ResponseResult(true,200,"查询资源信息成功",categoryList);

    }

    /*
        添加资源信息
    */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory category){

        if (category.getId() == null){
            resourceCgService.saveResourceCategory(category);

            return new ResponseResult(true,200,"添加资源信息成功",null);
        }else {
            resourceCgService.updateResourceCategory(category);

            return new ResponseResult(true,200,"更新资源信息成功",null);
        }

    }

    /*
      根据id回显资源信息
     */
    @RequestMapping("/findAllResourceCategoryById")
    public ResponseResult findAllResourceCategoryById(Integer id){

        ResourceCategory category = resourceCgService.findAllResourceCategoryById(id);

        return new ResponseResult(true,200,"根据id查询资源成功",category);

    }

    /*
       根据id删除资源信息
    */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategoryById(Integer id){


        resourceCgService.deleteResourceCategoryById(id);

        return new ResponseResult(true,200,"根据id删除资源成功",null);

    }

    /*
      根据categoryId查询资源信息
     */
    @RequestMapping("/findResourceByCid")
    public ResponseResult findResourceByCid(Integer cid){

        List<Resource> resourceByCid = resourceCgService.findResourceByCid(cid);

        return new ResponseResult(true,200,"响应成功",resourceByCid);
    }



}
