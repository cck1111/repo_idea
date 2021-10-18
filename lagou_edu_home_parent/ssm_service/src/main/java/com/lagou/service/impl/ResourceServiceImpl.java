package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    //多条件&分页查询
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        //分页助手
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> resourceList = resourceMapper.findAllResource(resourceVo);

        // 获取分页相关信息
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);

        return pageInfo;
    }
}
