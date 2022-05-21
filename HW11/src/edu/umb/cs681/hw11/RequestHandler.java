package edu.umb.cs681.hw11;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public void setDone() {
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}

	public void run() {
		
		String[] files = {"AccessCounter.class", 
						  "RequestHandler.class", 
						  "a.html", 
						  "b.html",
						  "c.html", 
						  "d.html"};
		AccessCounter objAccessCounter = AccessCounter.getInstance();
		
		while (true) {			
			lock.lock();
			try {
				if(done) {
	    			System.out.println("Threads execution complete");
	    			break;
	    		}
				
				int num = new Random().nextInt(files.length);
				Path path = FileSystems.getDefault().getPath(".", files[num]);				
				
				objAccessCounter.increment(path);
				System.out.println(files[num] + " path count: " + objAccessCounter.getCount(path));
			}
			finally {
				lock.unlock();
			}
			
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				System.out.println("Error in:"+Thread.currentThread().getName()+e.toString());
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		
		RequestHandler R1  = new RequestHandler();
		RequestHandler R2  = new RequestHandler();
		RequestHandler R3  = new RequestHandler();
		RequestHandler R4  = new RequestHandler();
		RequestHandler R5  = new RequestHandler();
		RequestHandler R6  = new RequestHandler();
		RequestHandler R7  = new RequestHandler();
		RequestHandler R8  = new RequestHandler();
		RequestHandler R9  = new RequestHandler();
		RequestHandler R10  = new RequestHandler();
		RequestHandler R11  = new RequestHandler();
		RequestHandler R12  = new RequestHandler();
		RequestHandler R13  = new RequestHandler();
		RequestHandler R14  = new RequestHandler();
		
		Thread T1  = new Thread(R1);
		Thread T2  = new Thread(R2);
		Thread T3  = new Thread(R3);
		Thread T4  = new Thread(R4);
		Thread T5  = new Thread(R5);
		Thread T6  = new Thread(R6);
		Thread T7  = new Thread(R7);
		Thread T8  = new Thread(R8);
		Thread T9  = new Thread(R9);
		Thread T10  = new Thread(R10);
		Thread T11  = new Thread(R11);
		Thread T12  = new Thread(R12);
		Thread T13  = new Thread(R13);
		Thread T14  = new Thread(R14);
		
		T1.start();
		T2.start();
		T3.start();
		T4.start();
		T5.start();
		T6.start();
		T7.start();
		T8.start();
		T9.start();
		T10.start();
		T11.start();
		T12.start();
		T13.start();
		T14.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		R1.setDone();
		R2.setDone();
		R3.setDone();
		R4.setDone();
		R5.setDone();
		R6.setDone();
		R7.setDone();
		R8.setDone();
		R9.setDone();
		R10.setDone();
		R11.setDone();
		R12.setDone();
		R13.setDone();
		R14.setDone();
		
		
		T1.interrupt();
		T2.interrupt();
		T3.interrupt();
		T4.interrupt();
		T5.interrupt();
		T6.interrupt();
		T7.interrupt();
		T8.interrupt();
		T9.interrupt();
		T10.interrupt();
		T11.interrupt();
		T12.interrupt();
		T13.interrupt();
		T14.interrupt();
		
		try {
			T1.join();
			T2.join();
			T3.join();
			T4.join();
			T5.join();
			T6.join();
			T7.join();
			T8.join();
			T9.join();
			T10.join();
			T12.join();
			T13.join();
			T14.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   		
	}	
}
