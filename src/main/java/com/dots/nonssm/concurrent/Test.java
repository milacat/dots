package com.dots.nonssm.concurrent;

public class Test {
    private static String  ss;
    public static void main(String[] args){
        System.out.println(get());
        System.out.println(check());

    }
    public static Object get(){
        Object c = null;
        try{
            c="12";
            return c;  //位置1
        }catch (Exception  e){
            c="13";
        }finally {
            c="123";
         //   return c;  // return 不同位置对结果的影响 这里的return覆盖了之前的return中c的值
        }
        return c;  //如果return在这个位置，执行完finally语句块后 不会执行到此 而是用的位置1的return返回c 值没有被覆盖
    }

    public static boolean check(){
     String ss2 = "";
     return (ss.equals(ss2));  //ss的默认值为null，所以会抛空指针异常
    }
}
