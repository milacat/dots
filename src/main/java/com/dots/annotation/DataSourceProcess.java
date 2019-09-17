package com.dots.annotation;


import com.dots.util.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class DataSourceProcess {
public void intercept(JoinPoint point) throws NoSuchMethodException {
    Class<?> target = point.getTarget().getClass();
    MethodSignature signature = (MethodSignature) point.getSignature();
    resolveDataSource(target, signature.getMethod());
   }
    private void resolveDataSource(Class<?> clazz, Method method) throws NoSuchMethodException {
     Class<?>[] types = method.getParameterTypes();
     if(clazz.isAnnotationPresent(DataSource.class)){
         DataSource source = clazz.getAnnotation(DataSource.class);
         DynamicDataSource.setDataSource(source.value());
     }
        // 方法注解可以覆盖类型注解
        Method m = clazz.getMethod(method.getName(), types);
        if (m != null && m.isAnnotationPresent(DataSource.class)) {
            DataSource source = m.getAnnotation(DataSource.class);
            DynamicDataSource.setDataSource(source.value());
        }
    }
}
