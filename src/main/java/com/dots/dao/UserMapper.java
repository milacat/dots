package com.dots.dao;

import com.dots.domain.City;
import com.dots.domain.Staff;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    City selectByName(String name);

    Staff selectById(Integer id);
}
