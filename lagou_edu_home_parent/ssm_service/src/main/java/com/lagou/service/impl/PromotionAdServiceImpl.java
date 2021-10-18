package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd> findAllAdByPage(PromotionAdVo promotionAdVo) {

        /*
                设置分页参数  1：当前页， 2：当前页数
                PageHelper.startPage(1,2);
         */
        //分页
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());

        // 一旦调用如上方法，下面就会执行分页查询操作
        List<PromotionAd> allAdByPage = promotionAdMapper.findAllAdByPage();

        //获取其他分页的数据  总页数 等...
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allAdByPage);

        return pageInfo;
    }

    //2.修改广告上下线
    @Override
    public void updatePromotionAdStatus(int id, int status) {

        //封装数据
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
