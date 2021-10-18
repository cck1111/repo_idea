package com.lagou.dao;

import com.lagou.domain.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TestMapper {

    public List<Test> findAll();
}
