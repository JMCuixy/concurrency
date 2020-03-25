package org.concurrency.demo.chapter4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author xiuyin.cui
 * @Description
 * @date 2019/3/5 21:20
 */
public class MultiThread {

    /**
     * @Description Java 程序天生就是多线程程序
     * <p>
     * [6] Monitor Ctrl-Break
     * [5] Attach Listener
     * [4] Signal Dispatcher
     * [3] Finalizer
     * [2] Reference Handler
     * [1] main
     */
    public static void main(String[] args) {
        // 获取Java线程管理的MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
