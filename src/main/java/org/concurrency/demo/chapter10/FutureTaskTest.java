package org.concurrency.demo.chapter10;

/**
 * @author : cuixiuyin
 * @date : 2019/9/21
 */
public class FutureTaskTest {
    // FutureTask 有三种状态
    // 1. 未启动。FutureTask.run()方法还没有被执行之前。执行FutureTask.cancel()方法将导致此任务永远不会被执行。
    // 2. 已启动。FutureTask.run()方法被执行。执行FutureTask.cancel（true）方法将以中断执行此任务线程；执行FutureTask.cancel（false）方法将不会对正在执行此任务的线程产生影响
    // 3. 已完成。FutureTask.run()方法执行完后正常结束，或被取消（FutureTask.cancel（…）），或执行FutureTask.run()方法时抛出异常而异常结束。执行FutureTask.cancel（…）方法将返回false。

    public static void main(String[] args) {

    }
}
