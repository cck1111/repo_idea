package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceCgMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceCgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCgServiceImpl implements ResourceCgService {

    @Autowired
    private ResourceCgMapper resourceCgMapper;

    /*
    查询所有资源分类信息
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> categoryList = resourceCgMapper.findAllResourceCategory();
        return categoryList;
    }


    /*
        添加资源信息
    */
    @Override
    public void saveResourceCategory(ResourceCategory category) {

        // 封装数据
        category.setCreatedBy("system");
        category.setUpdatedBy("system");

        Date date = new Date();
        category.setCreatedTime(date);
        category.setUpdatedTime(date);

        //调用方法
        resourceCgMapper.saveResourceCategory(category);

    }

    /*
      根据id回显资源信息
   */
    @Override
    public ResourceCategory findAllResourceCategoryById(Integer id) {
        ResourceCategory category = resourceCgMapper.findAllResourceCategoryById(id);
        return category;
    }

    /*
        更新资源信息
    */
    @Override
    public void updateResourceCategory(ResourceCategory category) {

        // 封装数据

        category.setUpdatedTime(new Date());

        resourceCgMapper.updateResourceCategory(category);
    }

    /*
       根据id删除资源信息
    */

    @Override
    public void deleteResourceCategoryById(Integer id) {

        resourceCgMapper.deleteResourceCategoryById(id);

    }

    /*
      根据categoryId查询资源信息
     */
    @Override
    public List<Resource> findResourceByCid(Integer cid) {
        List<Resource> resourceByCid = resourceCgMapper.findResourceByCid(cid);
        return resourceByCid;
    }


}
