import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.*;

/**
 * Created by bo.wu on 2017/5/17.
 *
 * 使用多线程的四种方法：
 * 1裸线程：适用于快速和简单的解决方案
 *
 * 2另一个选择是使用API来管理一组线程：Executor和CompletionService
 * ，Executors类提供了一组方法，能够创建拥有完善配置的线程池和executor。我们将使用newFixedThreadPool()，它创建预定义数量的线程，并不允许线程数量超过这个预定义值。这意味着，如果所有的线程都被使用的话，提交的命令将会被放到一个队列中等待；当然这是由executor来管理的。

 在它的上层，有ExecutorService管理executor的生命周期，以及CompletionService会抽象掉更多细节，作为已完成任务的队列。
 *
 * 要精确的控制程序产生的线程数量，以及它们的精确行为，那么executor和executor服务将是正确的选择。例如，需要仔细考虑的一个重要问题是，当所有线程都在忙于做其他事情时，需要什么样的策略？增加线程数量或者不做数量限制？把任务放入到队列等待？如果队列也满了呢？无限制的增加队列大小？

 感谢JDK，已经有很多配置项回答了这些问题，并且有着直观的名字，例如上面的Executors.newFixedThreadPool(4)。
 *
 *3通过并行流，使用ForkJoinPool (FJP)
 */



public class MultiThreaddemoFromBlog {
    private static String getFirstResult(String question, List<String> engines) {
        final AtomicReference<String> result = new AtomicReference<>();
//        for(String base: engines) {
//            String url = base + question;
//            new Thread(() -> {//新建一个线程
//                result.compareAndSet(null, WS.url(url).get());
//            }).start();
//        }
        while(result.get() == null); // wait for some result to appear
        return result.get();
    }



    private static String getFirstResultExecutors(String question, List<String> engines) {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(4));

//        for(String base: engines) {
//            String url = base + question;
//            service.submit(() -> {
////                return WS.url(url).get();
//            });
//        }
        try {
            return service.take().get();
        }
        catch(InterruptedException | ExecutionException e) {
            return null;
        }
    }

//    private static String getFirstResult(String question, List<String> engines) {
//        // get element as soon as it is available
//        Optional<String> result = engines.stream().parallel().map((base) -> {
//            String url = base + question;
//            return WS.url(url).get();
//        }).findAny();
//        return result.get();
//    }
//

}
