package com.example.lru_cache.lru;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    private Map<Integer, Node> numMap;
    private Integer cacheSize;

    private Integer linkedListSize;
    private Node head;
    private Node tail;


    public LruCache(Integer cacheSize) {
        this.numMap = new HashMap<>();
        this.cacheSize = cacheSize;
        this.head = null;
        this.tail = null;
        this.linkedListSize=0;
    }

    public String get(Integer num){
        Node node = numMap.get(num);
        promoteNode(node);
        if(node!=null){
            return node.str;
        }
        return null;
    }

    public void set(Integer num,String str){
        checkMapSize();
        Node node = numMap.get(num);
        if(node==null){
            node = new Node(num,str);
            addNodeAsTail(node);
            return;
        }
        promoteNode(node);
    }

    private void checkMapSize() {
        if(linkedListSize<cacheSize){
           return;
        }
        Node oldTail = tail;
        Node prev = tail.prev;
        prev.next=null;
        tail=prev;
        numMap.remove(oldTail.num);
        linkedListSize--;
        checkMapSize();
    }

    public void promoteNode(Node C){ // C
        if(C==null || C.prev==null ){
            return ;
        }
        Node B = C.prev;
        Node D = C.next;
        Node A = C.prev.prev;

        setNext(A,C);
        setNext(C,B);
        setNext(B,D);

        setPrev(D,B);
        setPrev(B,C);
        setPrev(C,A);

        updateHead(C);
        updateTail(C);

    }

    public void addNodeAsTail(Node F){
        if(tail==null){
            setTail(F);
            return;
        }
        F.prev=tail;
        F.next=null;
        tail.next=F;
        tail=F;
        numMap.put(F.num,F);
        linkedListSize++;
    }

    public void updateHead(Node node){
        if(head.prev!=null && node.prev==null) {
            head = node;
        }
    }

    public void updateTail(Node node){
        if(tail.next!=null && node.next==null) {
            tail = node;
        }
    }

    public void setTail(Node node){
        tail=node;
        if(head==null){
            head=tail;
        }
        numMap.put(node.num,node);
        this.linkedListSize++;
    }

    private void setNext(Node node,Node nextNode){
        if(node==null){
            return;
        }
        node.next=nextNode;
    }

    private void setPrev(Node node,Node prevNode){
        if(node==null){
            return;
        }
        node.prev=prevNode;
    }



    public void printLinkedList(){
        Node current = head;
        while(current!=null){
            System.out.println("[" + current.num + "," + current.str +"]");
            current=current.next;
        }
    }


}
