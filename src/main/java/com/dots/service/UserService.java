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

  //  @DataSource("dataSource1")
  //  dataSource1为默认数据库，无需指定，因为在使用完dataSource2后会切换成dataSource1数据源
    public City getCity(){
        return userMapper.selectByName("Abakan");
    }

    @DataSource("dataSource2")
    public Staff getStaff(){
        //未使用DataSource注解时，手动切换数据库
//        DynamicDataSource.setDataSource("dataSource2");
         Staff staff = userMapper.selectById(1);
//        DynamicDataSource.setDataSource("dataSource1");
        return staff;
    }

}