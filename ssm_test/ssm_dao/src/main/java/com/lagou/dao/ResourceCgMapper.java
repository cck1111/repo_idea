package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceCgMapper {

    // 作业一：完成资源管理功能

    /*
        查询所有资源分类的信息
     */
    public List<ResourceCategory> findAllResourceCategory();

    /*
        添加资源信息
     */
    public void saveResourceCategory(ResourceCategory category);

    /*
       根据id回显资源信息
     */
    public ResourceCategory findAllResourceCategoryById(Integer id);

    /*
        编辑资源信息
     */
    public void updateResourceCategory(ResourceCategory category);

    /*
        根据id删除资源信息
     */
    public void deleteResourceCategoryById(Integer id);


    /*
      根据categoryId查询资源信息
     */
     public List<Resource> findResourceByCid(Integer cid);

}
