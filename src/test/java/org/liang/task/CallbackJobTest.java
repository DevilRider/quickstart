package org.liang.task;

import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallbackJobTest {

    @Autowired
    private CallbackJob callback;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = callback.doTaskOne();
        Future<String> task2 = callback.doTaskTwo();
        Future<String> task3 = callback.doTaskThree();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            // Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}
