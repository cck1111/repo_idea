package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;


import java.util.List;

public interface ResourceService {

    /*
        分页查询&多条件查询
     */

    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);
}
