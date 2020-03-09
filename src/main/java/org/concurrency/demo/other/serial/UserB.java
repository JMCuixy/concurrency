package org.concurrency.demo.other.serial;

import lombok.Data;

import java.io.*;

/**
 * @author : cuixiuyin
 * @date : 2020/3/7
 */
@Data
public class UserB implements Externalizable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    private int age;
    private static String name = "李四";


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        age = in.readInt();
    }

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
        UserB user = new UserB();
        user.setAge(26);
        //序列化对象到文件中
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\userB"))) {
            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserialize() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\userB"))) {
            UserB user = (UserB) objectInputStream.readObject();
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
