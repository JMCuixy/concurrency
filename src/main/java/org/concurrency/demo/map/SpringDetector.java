package org.concurrency.demo.map;

/**
 * @author : cuixiuyin
 * @date : 2019/8/27
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Description:测试类
 */
public class SpringDetector {

    public static void main(String[] args) {
        detectSpring();
    }

    public static void detectSpring() {
        Map<Groundhog, Prediction> map = new HashMap(16);
        for (int i = 0; i < 10; i++) {
            map.put(new Groundhog(i), new Prediction());
        }
        System.out.println("map=" + map);

        Groundhog groundhog = new Groundhog(3);
        System.out.println();

        //查找这个key是否存在
        if (map.containsKey(groundhog)) {
            System.out.println(map.get(groundhog));
        } else {
            System.out.println("key not find:" + groundhog);
        }
    }
}
