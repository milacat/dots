package com.dots.annotation;


import com.dots.util.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect // for aop
@Component // for auto scan
public class DataSourceProcess {
    @Pointcut("execution(public * com.dots.service.UserService.*(..))")
    public void pointCut() {
    };

    @Before("pointCut()")
    public void beforeInvoke(JoinPoint jp) throws ClassNotFoundException {
       String methodName = jp.getSignature().getName();
       String clazzName = jp.getTarget().getClass().getName();
       Class targetClazz = Class.forName(clazzName);
       Method[] methods = targetClazz.getMethods();
       for(Method method : methods){
           if(method.getName().equals(methodName)){
               if(method.isAnnotationPresent(DataSource.class)){
                   DataSource dataSource = method.getAnnotation(DataSource.class);
                   DynamicDataSource.setDataSource(dataSource.value());
               }
               break;
           }
       }
    }

    //切换回默认数据库
    @After("pointCut()")
    public void afterInvoke(JoinPoint jp) throws ClassNotFoundException {
        String clazzName = jp.getTarget().getClass().getName();
        Class targetClazz = Class.forName(clazzName);
        Method[] methods = targetClazz.getMethods();
        String methodName = jp.getSignature().getName();
        for(Method method : methods){
            if(methodName.equals(method.getName())) {
                if (method.isAnnotationPresent(DataSource.class)) {
                    DynamicDataSource.setDataSource("dataSource1");
                }
                break;
            }
         }
    }
}
