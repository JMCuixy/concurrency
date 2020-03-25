package org.concurrency.demo;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : cuixiuyin
 * @date : 2020/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuasarTest {

    @Test
    public void test() {
        Fiber<Void> fiber = new Fiber<Void>() {
            @Override
            protected Void run() throws SuspendExecution, InterruptedException {
                System.out.println("Hello Fiber");
                return null;
            }
        };
        fiber.start();
    }
}
