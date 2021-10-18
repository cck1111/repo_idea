package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    /*
         1.查询所有的广告位
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        2.添加广告位
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        2.1根据id查询广告位信息
     */
    public PromotionSpace findPromotionSpaceById(int id);


    //2.2 更新广告位名称
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
