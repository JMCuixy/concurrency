package org.algorithm.divideAndConquer;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/11 11:11
 * @Description: 在数组 { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 } 中，查找 8 是否出现过。
 */
public class Test1 {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Task task = new Task(arr, 0, arr.length, 8);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Boolean invoke = forkJoinPool.invoke(task);
        System.out.println(invoke);
    }


    public static class Task extends RecursiveTask<Boolean> {

        /**
         * 阈值
         */
        private static final Integer THRESHOLD = 5;

        private Integer[] array;
        private Integer start;
        private Integer end;

        private Integer obj;

        public Task(Integer[] array, Integer start, Integer end, Integer obj) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.obj = obj;
        }


        @Override
        protected Boolean compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    if (array[i].equals(obj)) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            } else {
                int middle = (start + end) >>> 1;
                Task leftTask = new Task(array, start, middle, obj);
                Task rightTask = new Task(array, middle, end, obj);

                // invokeAll 方法使用
                invokeAll(leftTask, rightTask);

                //等待子任务执行完，并得到其结果
                Boolean leftJoin = leftTask.join();
                Boolean rightJoin = rightTask.join();
                return leftJoin | rightJoin;
            }
        }
    }
}
