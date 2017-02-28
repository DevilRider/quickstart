package org.liang.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@Configuration
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
