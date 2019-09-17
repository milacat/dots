package com.dots.annotation;

public class DataSourceSwitch {

    @DataSource("mysql_db1")
    public static String  selectfromDb1(){
        return "1";
    }

    @DataSource("mysql_db2")
    public static String  selectfromDb2(){
        return "2";
    }

    public static void  main(String[] args){
        String s1 = selectfromDb1();
        String s2 = selectfromDb2();

    }


}
