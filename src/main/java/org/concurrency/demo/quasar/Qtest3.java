package org.concurrency.demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : cuixiuyin
 * @date : 2020/3/25
 */
@Component
public class Qtest3 {

    @PostConstruct
    public void init() {
        new Fiber<>(() -> {
            System.out.println("Hello Fiber");
            return null;
        }).start();
    }

}
