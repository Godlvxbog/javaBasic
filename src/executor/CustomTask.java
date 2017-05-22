package executor;

import java.util.concurrent.CountDownLatch;

/**
 * Created by bo.wu on 2017/5/19.
 * 抽象函数，抽象类
 * run里面是执行体，
 * 线程执行一般记得写，try -- catch -- finally
 *
 * 任务需要包含的有：
 * 1、实现runnable作为线程类，
 * 2、包含一个countdownlatch作为闭锁计数器
 * 3、构造方法传入一个countdownlatch对象初始化对象
 * 4、@run执行体里面，添加dowork接口，+ countdown的方法
 */
public abstract class CustomTask implements  Runnable{
    private final CountDownLatch countDownLatch ;

    public CustomTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    //一个抽象函数
    public abstract void doWork();

    @Override
    public void run() {
        try{
            doWork();
        }catch (Exception e){
            System.out.println("log：执行线程失败");
        }
        finally {
//            通知机制是通过 CountDownLatch.countDown()方法：countlatch只是实现计数器的功能
            this.countDownLatch.countDown();
        }
    }

}
