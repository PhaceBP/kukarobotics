package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {

	
	public static void main(String[]args) throws InterruptedException, ExecutionException{
		
		ThreadPoolExecutor executor =
				new ThreadPoolExecutor(5, 10, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		
		Future<String> future = executor.submit(new Callable<String>() {

			public String call() throws Exception {
				Thread.sleep(2000);
				return "Hello";
			}
		});
		
		while(!future.isDone()){
			System.out.println("Working....");
		}
		
		System.out.println(future.get());
		
		executor.shutdown();
	}
}
