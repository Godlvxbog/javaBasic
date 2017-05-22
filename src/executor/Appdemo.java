package executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bo.wu on 2017/5/19.
 * 创建一个多线程执行的demo：
 *
 * 1 一个可以执行的执行体 ：task implement Runnable:其实就是一个线程了
 * 2 用用多线程框架 去执行 executeService.excute(task);
 * 3 理解抽象类:抽象函数 --抽象类
 *
 *
 * 可以为你的下次面试准备以下一些CountDownLatch相关的问题：

 解释一下CountDownLatch概念?
 CountDownLatch 和CyclicBarrier的不同之处?
 给出一些CountDownLatch使用的例子?
 CountDownLatch 类中主要的方法?
 *
 */
public class Appdemo {
    public static void main(String[] args) {

        int threadnum =3;
        CountDownLatch countDownLatch = new CountDownLatch(threadnum);
        List<CustomTask> tasks = new ArrayList<>();

        //建立一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(threadnum);

        //添加计数器
        Integer count = 0;

        //添加任务
        tasks.add(new CustomTask(countDownLatch) {
            @Override
            public void doWork() {
                for (int i=0; i < 10; i++ ){
                    System.out.println(Thread.currentThread().getName()+ "任务一：第 "+i + "次打印");
                }
            }
        });

        tasks.add(new CustomTask(countDownLatch) {
            @Override
            public void doWork() {
                for (int i=0; i < 10; i++ ){
                    System.out.println(Thread.currentThread().getName()+"任务二：第 "+i + "次打印");
                }
            }
        });

        tasks.add(new CustomTask(countDownLatch) {
            @Override
            public void doWork() {
                for (int i=0; i < 10; i++ ){
                    System.out.println(Thread.currentThread().getName()+"任务三：第 "+i + "次打印");
                }
            }
        });

        //执行任务
        for (CustomTask task : tasks){
//            task.doWork();//单线程执行任务
            executorService.execute(task);//用线程池执行任务
        }

        System.out.println(Thread.currentThread().getName()+"主线程的测试");


    }
}
