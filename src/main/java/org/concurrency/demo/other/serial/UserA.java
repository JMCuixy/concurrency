package org.concurrency.demo.other.serial;

import lombok.Data;

import java.io.*;

/**
 * @author : cuixiuyin
 * @date : 2020/3/6
 */
@Data
public class UserA implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    private int age;
    private static String name = "张三";

    @Override
    public String toString() {
        return "User{age=" + age + "，name=" + name + "}";
    }



    public static void main(String[] args) {
        // 序列化
        serialize();
        // 反序列化
        deserialize();
    }

    private static void serialize() {
        UserA user = new UserA();
        user.setAge(26);
        //序列化对象到文件中
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\userA"))) {
            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserialize() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\userA"))) {
            UserA user = (UserA) objectInputStream.readObject();
            System.out.println(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
