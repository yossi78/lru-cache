package com.example.lrucache.sdk;
import java.util.LinkedList;


public class DoubleLinkedList<T> {

    private Node<T> head ;
    private Node<T> tail ;



    public Node<T> addHead(Node<T> node){
        if(head!=null){
            head.setPrev(node);
        }
        node.setNext(head);
        head=node;
        refreshHeadTail();
        return head;
    }

    public Node<T> addHead(T t){
        Node<T> node =new Node<>(t);
        return addHead(node);
    }


    public Node<T> addTail(Node<T> node){
        if(tail!=null){
            tail.setNext(node);
            node.setPrev(tail);
        }
        tail=node;
        refreshHeadTail();
        return tail;
    }

    public Node<T> addTail(T t){
        Node<T> node =new Node<>(t);
        return addTail(node);
    }


    public Node<T> getHead(){
        return head;
    }

    public Node<T> getTail(){
        return tail;
    }

    public Node<T> removeHead(){
        if(head!=null){
            head.getNext().setPrev(null);
            head=head.getNext();
        }
        return head;
    }


    public Node<T> removeTail(){
        Node<T> prevTail = tail!=null ? tail.getPrev() : null;
        if(prevTail!=null){
            prevTail.setNext(null);
            tail=prevTail;
            return tail;
        }
        if(tail.equals(head)){
            head=null;
        }
        tail=null;
        return tail;
    }


    public void remove(Node<T> node){
        Node<T> prevNode = node!=null ? node.getPrev() : null;
        Node<T> nextNode = node!=null ? node.getNext() : null;
        if(prevNode!=null){
            prevNode.setNext(nextNode);
        }
        if(nextNode!=null){
            nextNode.setPrev(prevNode);
        }
        if(node.equals(head)){
            head=null;
        }
        if(node.equals(tail)){
            tail=null;
        }
        node=null;
        refreshHeadTail();
    }

    public Boolean isContains(Node<T> node){
        Node<T> current=head;
        while(current!=null){
            if(current.equals(node)){
                return true;
            }
            current=current.getNext();
        }
        return false;
    }

    public Boolean isContains(T t){
        return isContains(new Node<>(t));
    }

    public Node<T> moveNodeBackward(Node<T> node){
        Node<T> prev = node!=null ? node.getPrev() : null;
        Node<T> next = node!=null ? node.getNext() : null;
        Node<T> prevPrev = prev!=null ? prev.getPrev() : null;
        if(prevPrev!=null){
            prevPrev.setNext(node);
        }
        node.setPrev(prevPrev);
        if(prev!=null){
            prev.setPrev(node);
            prev.setNext(next);
            node.setNext(prev);
        }
        if(next!=null && prev!=null){
            next.setPrev(prev);
        }
        refreshHeadTail();
        return node;
    }

    private void refreshHeadTail() {
        refreshHead();
        refreshTail();
    }

    private void refreshHead(){
        Node<T> currentHead=head;
        while(currentHead!=null && currentHead.getPrev()!=null){
            currentHead=currentHead.getPrev();
        }
        head=currentHead;
        if(head==null){
            head=tail;
        }
    }

    private void refreshTail(){
        Node<T> currentTail=tail;
        while(currentTail!=null && currentTail.getNext()!=null){
            currentTail=currentTail.getNext();
        }
        tail=currentTail;
        if(tail==null){
            tail=head;
        }
    }

    public Node<T> moveNodeForward(Node<T> node){
        Node<T> nextNode = node!=null ? node.getNext() : null;
        Node<T> nextNextNode = nextNode!=null ? nextNode.getNext() : null;
        if(nextNextNode!=null){
            nextNextNode.setPrev(node);
            node.setNext(nextNextNode);
            node.setPrev(nextNode);
        }
        if(nextNode!=null){
            nextNode.setNext(node);
        }
        refreshHeadTail();
        return node;
    }


    public Integer getSize(){
        Integer size=0;
        Node<T> current=head;
        while(current!=null){
            size++;
            current=current.getNext();
        }
        return size;
    }


    public void printDoubleLinkedList(Boolean withPrev){
        Node<T> current=head;
        while(current!=null){

            Node<T> preNode = current.getPrev()!=null ? current.getPrev() : null;
            String prevData = preNode!=null ? String.valueOf(preNode.getData()) : "";
            System.out.print(current.getData() );
            if(withPrev){
                System.out.print(" (p="+prevData + ")");
            }
            if(current.getNext()!=null){
                System.out.print(" -> ");
            }
            current=current.getNext();
        }
        System.out.println("\n---------------------------------------------------------");
    }



    public Node moveNodeToBeHead(Node node) {
        if(node.equals(head)){
            return head;
        }
        Node<T> prevNode = node!=null ? node.getPrev() : null;
        Node<T> nextNode = node!=null ? node.getNext() : null;
        if(prevNode!=null){
            prevNode.setNext(nextNode);
        }
        if(nextNode!=null){
            nextNode.setPrev(prevNode);
        }
        node.setPrev(null);
        node.setNext(head);
        if(head!=null){
            head.setPrev(node);
        }
        head=node;
        refreshHeadTail();
        return head;
    }




    public static void main(String[] args) {
        DoubleLinkedList<Integer>  doubleLinkedList=new DoubleLinkedList<>();
        doubleLinkedList.addTail(100);
        doubleLinkedList.addTail(200);
        doubleLinkedList.addTail(300);
        doubleLinkedList.addTail(400);
        doubleLinkedList.addTail(500);
        doubleLinkedList.printDoubleLinkedList(false);
        doubleLinkedList.printDoubleLinkedList(true);

    }


}
