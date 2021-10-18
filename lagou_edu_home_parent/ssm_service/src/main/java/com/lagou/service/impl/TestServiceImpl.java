package com.lagou.service.impl;

import com.lagou.dao.TestMapper;
import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> findAll() {

        List<Test> testList = testMapper.findAll();
        // System.out.println(66666);
        return testList;
    }
}
