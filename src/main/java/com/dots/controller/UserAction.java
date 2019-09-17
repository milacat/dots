package com.dots.controller;

import com.dots.domain.City;
import com.dots.domain.Staff;
import com.dots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    public ModelAndView dataSourceSwitch(){
        City city = userService.getCity();
        Staff staff = userService.getStaff();

        ModelAndView mv = new ModelAndView("helloSpring");//指定视图
        mv.addObject("message", "所在城市Abakan的Code："+city.getCountryCode());
        mv.addObject("name", "员工的姓名："+staff.getFirstName());
        return mv;
    }
}
