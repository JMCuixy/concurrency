package org.data.structure;

import lombok.Data;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/6 17:19
 * @Description:
 */
@Data
public class Node {

    public Object value;

    public Node next;

    public Node(Object value) {
        this.value = value;
        next = null;
    }

    @Override
    public String toString() {
        String value = String.valueOf(this.value);
        if (this.next != null) {
            value += this.next.toString();
        }
        return value;
    }
}
