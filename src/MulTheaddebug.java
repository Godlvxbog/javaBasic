/**
 * Created by bo.wu on 2017/4/21.
 */
public class MulTheaddebug {
    public static void main(String[] args) throws InterruptedException {
        new Thread() { // 断点0
            @Override
            public void run() {
                System.out.println("1"); // 断点1

//              Thread.sleep(2000);
                System.out.println("2"); // 断点2
            }
        }.start();
        // 外线程
        System.out.println("3"); // 断点3
//        Thread.sleep(2000);
        System.out.println("4"); // 断点4
    }
}
