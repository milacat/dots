package com.dots.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented  //使注解DataSource成为公共api
public @interface DataSource {
    String value();
}
