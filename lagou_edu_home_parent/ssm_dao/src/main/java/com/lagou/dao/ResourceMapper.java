package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;


import java.util.List;

public interface ResourceMapper {

    /*
        分页&多添建查询
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);
}
