package com.example.lrucache.sdk;



public class Node<T>  {

    private T data =null;
    private Node prev=null;
    private Node next=null;


    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node prev, Node next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }





}
