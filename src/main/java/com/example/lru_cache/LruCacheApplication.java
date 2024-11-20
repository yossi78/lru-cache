package com.example.lru_cache;

import com.example.lru_cache.lru.LruCache;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LruCacheApplication {

	public static void main(String[] args) {
		LruCache lruCache = new LruCache(5);
		lruCache.set(1,"A");
		lruCache.set(2,"B");
		lruCache.set(3,"C");
		lruCache.set(4,"D");
		lruCache.set(5,"E");

		System.out.println("--------------------[1,2,3,4,5]--------------------------------------------------");
		lruCache.printLinkedList();

		System.out.println("--------------------[1,3,2,4,5]--------------------------------------------------");
		lruCache.get(3);
		lruCache.printLinkedList();

		System.out.println("--------------------[3,1,2,4,5]--------------------------------------------------");
		lruCache.get(3);
		lruCache.printLinkedList();

		System.out.println("--------------------[3,1,2,4,5]--------------------------------------------------");
		lruCache.get(3);
		lruCache.printLinkedList();


		System.out.println("--------------------[3,1,2,5,4]--------------------------------------------------");
		lruCache.get(5);
		lruCache.printLinkedList();

		System.out.println("---------------------------------------------------------------------------------");
	}


}
