package executor;

import java.util.concurrent.CountDownLatch;

/**
 * Created by bo.wu on 2017/5/19.
 * 抽象函数，抽象类
 * run里面是执行体，
 * 线程执行一般记得写，try -- catch -- finally
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
