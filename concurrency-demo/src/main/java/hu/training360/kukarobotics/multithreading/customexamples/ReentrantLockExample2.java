package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(2);

		ReentrantLock lock = new ReentrantLock();

		es.submit(() -> {

			lock.lock();

			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});
		
		es.submit(() -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println("Is locked? ==> "+lock.isLocked());
			System.out.println("Lock held by me? ==> "+lock.isHeldByCurrentThread());
			boolean locked = lock.tryLock();
			System.out.println("Lock acquired : "+locked);
		});
	}

}
