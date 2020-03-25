package org.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author : cuixiuyin
 * @date : 2020/3/24
 */
public class AgentStart {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        VirtualMachine virtualMachine = null;
        try {
            // 14241 是进程号
            virtualMachine = VirtualMachine.attach("1100");
            // 第一个参数是 agent jar包路径，第二个参数为传入 agentmain 的 args 参数
            virtualMachine.loadAgent("D:\\concurrency-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "test");
        } finally {
            if (virtualMachine != null) {
                virtualMachine.detach();
            }
        }

    }
}
