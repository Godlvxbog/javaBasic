package countDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bo.wu on 2017/5/3.
 * countDownLatch
 * ExecutorService(类似于线程池)
 *
 *
 */
public class CountdownLatch {

    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁 :闭锁
        final CountDownLatch begin = new CountDownLatch(1);

        // 结束的倒数锁
        final CountDownLatch end = new CountDownLatch(10);

        // 十名选手 ：执行体
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int index = 0; index < 10; index++) {
            final int NO = index + 1;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // await（）的调用者将会被阻塞
                        // 等待
                        begin.await();
                        Thread.sleep((long) (Math.random() * 10000));//模拟每个运动员进行的跑步所需要的时间
                        System.out.println("No." + NO + " arrived");
                    } catch (InterruptedException e) {
                    } finally {
                        // 每个选手到达终点时，end就减一
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println("Game Start");
        // begin减一，开始游戏 ，begin一开始就是1，所以需要先提前加一个
        begin.countDown();
        // 等待end变为0，即所有选手到达终点
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }

}
