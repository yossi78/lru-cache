package com.example.lru_cache.lru;

public class Node {
    public Integer num;
    public String str;
    public Node next;
    public Node prev;

    public Node(Integer num, String str) {
        this.num = num;
        this.str = str;
        this.prev=null;
        this.next=null;
    }

    public Node(Integer num, String str, Node nextNode, Node prev) {
        this.num = num;
        this.str = str;
        this.next = nextNode;
        this.prev = prev;
    }
}
