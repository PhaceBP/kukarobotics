package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorServiceExample {

	public static void testExecutorServiceWith10ThreadAndRunnable() {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {

			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("Async task...");
				} catch (InterruptedException e) {
					System.out.println("Task interrupted...");
				}

			}
		});

	}
	
	public static void testExecutorServiceWith10ThreadAndCallable() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Future<String> task = executorService.submit(new Callable<String>() {

			public String call() throws Exception {
				System.out.println("Important callable task...");
				Thread.sleep(4000);
				return "important";
			}
		});

		while (!task.isDone()) {

			System.out.println("Waiting for the result!");
			Thread.sleep(1000);
		}

		System.out.println(task.get());
		
		executorService.shutdown();

	}
	
	public static void testExecutorServiceWith10ThreadAndCallableInvokeAll() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newFixedThreadPool(10);

		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		for (int i = 0; i < 10; i++) {

			callables.add(new Callable<String>() {

				public String call() throws Exception {
					System.out.println("Important callable task...");
					Thread.sleep(4000);
					return UUID.randomUUID().toString();
				}
			});
		}

		List<Future<String>> result = executorService.invokeAll(callables);

		for (Future<String> f : result) {

			System.out.println(f.get());
		}

	}

	
	
	public  static void main(String[]args) throws InterruptedException, ExecutionException{
		testExecutorServiceWith10ThreadAndCallableInvokeAll();
	}
}
