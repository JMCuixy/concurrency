package org.concurrency.demo.map;

/**
 * @author : cuixiuyin
 * @date : 2019/8/27
 */

/**
 * Description:新建一个类作为map的key
 */
public class Groundhog {
    protected int number;

    public Groundhog() {
    }

    public Groundhog(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Groundhog{" + "number=" + number + '}';
    }
}
