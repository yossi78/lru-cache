package com.example.lrucache.lruLab;

import com.example.lrucache.sdk.DoubleLinkedList;
import com.example.lrucache.sdk.Node;

import java.util.HashMap;
import java.util.Map;



class LRUCache {

    private Integer capacity;

    private Map<Integer, Node<Integer>> nodeMap =new HashMap<>();
    private Map<Integer , Integer> numMap =new HashMap<>();


    private DoubleLinkedList<Integer> doubleLinkedList=new DoubleLinkedList<>();

    public LRUCache(int capacity) {
        this.capacity=capacity;
    }


    public void put(int key, int value) {
        System.out.println("ADD NUMBER KEY= "+key + " VALUE="+value);
        if(numMap.containsKey(key)){
            Node<Integer> alreadExistNode = nodeMap.get(key);
            numMap.remove(key);
            nodeMap.remove(key);
            doubleLinkedList.remove(alreadExistNode);
        }
        if(numMap.size()>=capacity){
            Node<Integer> lastNode = doubleLinkedList.getTail();
            Integer lastNodeKey =lastNode.getData();
            String lastNodeValueStr = "";
            if(lastNode!=null && lastNode.getData()!=null){
                lastNodeValueStr = String.valueOf(numMap.get(lastNodeKey));
            }
            System.out.println("CAPACITY FULL - REMOVE LAST NODE , key="+ lastNodeKey + " value=" + lastNodeValueStr);
            numMap.remove(lastNodeKey);
            nodeMap.remove(lastNodeKey);
            doubleLinkedList.removeTail();
        }
        Node<Integer> firstNode = doubleLinkedList.addHead(key);
        numMap.put(key,value);
        nodeMap.put(key,firstNode);
        doubleLinkedList.printDoubleLinkedList(true);
    }



    private void printMap(String title) {
        System.out.println(title);
        for(Integer currentKey : numMap.keySet()){
            System.out.println(currentKey + "[" +  numMap.get(currentKey)   + "] - ");
        }
        System.out.println("-------------------------------");
    }


    public int get(int key) {
        System.out.println("GET NUMBER="+key);
        if(numMap.containsKey(key)){
            Node<Integer> node = nodeMap.get(key);
            Integer value = numMap.get(key);
            System.out.println("MOVE NUMBER TO BE HEAD="+key);
            Node result = doubleLinkedList.moveNodeToBeHead(node);
            numMap.put(key,value);
            nodeMap.put(key,result);
            doubleLinkedList.printDoubleLinkedList(true);
            return value;
        }
        System.out.println("NUMBER DOES NOT EXIST = "+key);
        System.out.println("-------------------------------");
        return -1;
    }


    public static void main(String[] args) {
        LRUCache lruCache=new LRUCache(2);
        lruCache.put(2,1);
        lruCache.put(1,1);
        lruCache.put(2,3);
        lruCache.put(4,1);
        lruCache.get(1);
        lruCache.get(2);


    }




}
