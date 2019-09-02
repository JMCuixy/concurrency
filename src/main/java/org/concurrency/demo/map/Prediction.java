package org.concurrency.demo.map;


/**
 * Description:新建一个类作为 map 的value
 */
public class Prediction {

    private boolean shadow = Math.random() > 0.5;

    @Override
    public String toString() {
        if (shadow) {
            return "Six more weeks of Winter";
        }
        return "Early Spring!";
    }
}
