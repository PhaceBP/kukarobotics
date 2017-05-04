package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerExample {

	private static final int NUM_OF_INCS = 1000;

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void testModification() {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, NUM_OF_INCS).forEach(i -> {

			Runnable task = () ->

			atomicInteger.updateAndGet(n -> n + 2);
			executor.submit(task);

		});

		System.out.println("Update result : " + atomicInteger.get());
	}

	public static void testIncrementAndGet() throws InterruptedException {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, NUM_OF_INCS).forEach(i -> {
			executor.submit(atomicInteger::incrementAndGet);
		});
		
		executor.shutdown();

		while(!executor.isTerminated()){
			System.out.println("Waiting for the result....");
		}
		
		System.out.println("Increment and get result is : "+atomicInteger.get());
	}

	public static void main(String[] args) throws InterruptedException {
		testIncrementAndGet();
	}
}
