package org.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author : cuixiuyin
 * @date : 2020/3/24
 */
public class AgentTest {
    /**
     * 以 vm 参数的方式载入，在 java 程序的 main 方法执行之前执行
     *
     * @param agentArgs
     * @param inst      Agent技术主要使用的 api，我们可以使用它来改变和重新定义类的行为
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain start");

        System.out.println(agentArgs);
    }

    /**
     * 以Attach的方式载入，在Java程序启动后执行
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain start");

        System.out.println(agentArgs);
    }
}
