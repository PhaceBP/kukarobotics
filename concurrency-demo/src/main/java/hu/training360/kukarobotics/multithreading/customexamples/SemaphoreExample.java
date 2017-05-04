package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample {

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(10);

		Semaphore sm = new Semaphore(5);

		Runnable longRunningTask = () -> {

			boolean permit = false;

			try {
				permit = sm.tryAcquire(1, TimeUnit.SECONDS);

				if (permit) {
					System.out.println("Semaphore acquired");
					Thread.sleep(5000);
				} else {
					System.out.println("Could not acquire semaphore");
				}
			} catch (Exception e) {

			} finally {
				if (permit) {
					sm.release();
				}
			}
		};
		
		
		IntStream.range(0, 10).forEach(i -> es.submit(longRunningTask));
	}
}
