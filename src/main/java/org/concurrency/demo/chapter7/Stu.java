package org.concurrency.demo.chapter7;

/**
 * @author : cuixiuyin
 * @date : 2019/9/7
 */
public class Stu {

    private Long id;

    public volatile String name;

    public Stu() {
    }

    public Stu(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stu{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
