package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(2);

		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		Map<String, String> map = new HashMap<>();

		es.submit(() -> {

			lock.writeLock().lock();
			try {

				Thread.sleep(2000);
				System.out.println("Writer thread name: "+Thread.currentThread().getName());
				map.put("a", "b");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		});
		
		
		Runnable readTask = () -> {
			
			lock.readLock().lock();
			try {
				System.out.println("Reader thread name: "+Thread.currentThread().getName()+"/"+map.get("a"));
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
		};
		
		es.submit(readTask);
		es.submit(readTask);
		
		es.shutdown();
	}

}
