package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页获取广告列表
     */
    public List<PromotionAd> findAllAdByPage();

    /*
        修改广告上下线状态
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
