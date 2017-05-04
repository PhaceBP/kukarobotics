package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

	private int counter = 10;

	private ReentrantLock lock = new ReentrantLock();

	private class Incrementor implements Runnable {

		@Override
		public void run() {
			incrementValue();
		}

	}
	
	private class Decrementor implements Runnable {

		@Override
		public void run() {
			decrementValue();
		}

	}

	public void incrementValue() {

		lock.lock();

		try {
			System.out.println("Increment...");
			counter++;
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}

	}
	
	public void decrementValue() {

		lock.lock();
		try {
			System.out.println("Decrement...");
			counter--;
			counter--;
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}

	}

	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) throws InterruptedException {

		ReentrantLockExample e = new ReentrantLockExample();
		
		Thread t1 = new Thread(e.new Incrementor());
		Thread t2 = new Thread(e.new Decrementor());
	
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Counter value is : "+e.getCounter());
	}

}
