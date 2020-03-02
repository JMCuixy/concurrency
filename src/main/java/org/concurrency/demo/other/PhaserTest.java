package org.concurrency.demo.other;

/**
 * @author : cuixiuyin
 * @date : 2020/3/2
 * @description
 * 1. Phaser：多阶段栅栏，可以在初始时设定参与线程数，也可以中途注册/注销参与者，当到达的参与者数量满足栅栏设定的数量后，会进行阶段升级（advance）。
 * 1.1 phase(阶段)：在任意时间点，Phaser 只处于某一个 phase(阶段)，初始阶段为 0，最大达到 Integerr.MAX_VALUE，然后再次归零。当所有 parties 参与者都到达后，phase 值会递增。
 * 1.2 parties(参与者)：参与线程。Phaser 既可以在初始构造时指定参与者的数量，也可以中途通过register、bulkRegister、arriveAndDeregister等方法注册/注销参与者。
 * 1.3 arrive(到达)：Phaser 注册完 parties（参与者）之后，参与者的初始状态是 unarrived 的，当参与者到达（arrive）当前阶段（phase）后，状态就会变成 arrived。
 * 1.4 advance(进阶)：当阶段的到达参与者数满足条件后（注册的数量等于到达的数量），阶段就会发生进阶（advance）——也就是phase值+1。
 * 1.5 termination（终止）：代表当前 Phaser 对象达到终止状态。
 * 1.6 Tiering（分层）：一种树形结构，通过构造函数可以指定当前待构造的 Phaser对象的父结点。
 * 1.6.1：之所以引入 Tiering，是因为当一个 Phaser 有大量参与者（parties）的时候，内部的同步操作会使性能急剧下降，而分层可以降低竞争，从而减小因同步导致的额外开销。
 * 1.6.2：在一个分层 Phasers 的树结构中，注册和撤销子Phaser或父Phaser是自动被管理的。当一个Phaser的参与者（parties）数量变成0时，如果有该Phaser有父结点，就会将它从父结点中溢移除。
 */
public class PhaserTest {
}
