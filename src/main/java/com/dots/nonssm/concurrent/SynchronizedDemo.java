package com.dots.nonssm.concurrent;

public class SynchronizedDemo implements Runnable{

    //使用volatile关键字修饰count变量，increase方法不用synchronized 关键字修饰，执行结果小于200000
    //因为volatile虽然能保证可见性和禁止重排序，但是并不能保证原子性
    //public static volatile  int count = 0;

    public static int count = 0;

    @Override
    public void run() {

        String threadName = Thread.currentThread().getName();
        if(threadName!=null && threadName.contains("instance")){
            for(int j=0; j<1000000; j++){
                increase();
            }
        }
        else if(threadName!=null && threadName.contains("static")){
            for(int j=0; j<1000000; j++){
                increase2();
            }
        }
    }

    //当前对象的锁便是实例对象sync
    public synchronized void increase(){
        count++;
    }

    public synchronized static void increase2(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        //synchronized作用于实例方法 锁住的对象是实例sync
        SynchronizedDemo sync = new SynchronizedDemo();
        Thread t1 = new Thread(sync,"instance-t1");
        Thread t2 = new Thread(sync,"instance-t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("synchronized作用于实例方法，count: "+ count);

        count = 0; //置0
        //synchronized作用于实例方法 锁住的对象是类的Class对象 虽然线程中的实例不一样，但被锁的对象却是相同的
        Thread t3 = new Thread(new SynchronizedDemo(),"static-t3");
        Thread t4 = new Thread(new SynchronizedDemo(),"static-t4");
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println("synchronized作用于静态方法，count: "+ count);

        //也可在方法中支队某几行代码使用synchronized关键字
//        synchronized(被锁目标，可以是this关键字，实例名，或者XXX.class){
//            //...语句块
//        }

    }
}
