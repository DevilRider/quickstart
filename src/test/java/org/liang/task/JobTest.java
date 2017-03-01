package org.liang.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {

    @Autowired
    private Job job;

    @Test
    public void test() throws Exception {
        job.doTaskOne();
        job.doTaskTwo();
        job.doTaskThree();
    }

}
