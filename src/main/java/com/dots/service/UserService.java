package com.dots.service;

import com.dots.annotation.DataSource;
import com.dots.domain.City;
import com.dots.domain.Staff;
import com.dots.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSource("dataSource1")
    public City getCity(){
        return userMapper.selectByName("Abakan");
    }

    @DataSource("dataSource2")
    public Staff getStaff(){
        return userMapper.selectById(1);
    }

}
