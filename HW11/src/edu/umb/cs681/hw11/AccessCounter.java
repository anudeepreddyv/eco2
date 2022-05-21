package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	
	private Map<java.nio.file.Path, Integer> mapHashMap = new HashMap<java.nio.file.Path, Integer>();
	private ReentrantLock lock = new ReentrantLock();
	private static ReentrantLock staticLock = new ReentrantLock();
	private static AccessCounter instance = null;
	private AccessCounter() {};	
	
	public static AccessCounter getInstance() {
		staticLock.lock();
		try {
			if (instance == null)
				instance = new AccessCounter();
			return instance;
		}
		finally {
			staticLock.unlock();
		}
	}
	
	public void increment(Path path) {
		lock.lock();
		try {
			if (mapHashMap.get(path) != null)
				mapHashMap.put(path, mapHashMap.get(path) + 1);
			else 
				mapHashMap.put(path, 1);
		}
		finally {
			lock.unlock();
		}
	}
	
	public int getCount(Path path) {
		lock.lock();
		try {
			if (mapHashMap.get(path) != null)
				return mapHashMap.get(path);
			else
				return 0;
		}
		finally {
			lock.unlock();
		}
	}	
}
