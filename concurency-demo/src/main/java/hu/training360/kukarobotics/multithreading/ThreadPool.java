package hu.training360.kukarobotics.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

	private BlockingQueue<Runnable> taskQueue = null;

	private ThreadGroup poolGroup = new ThreadGroup("pool");

	private List<PoolThread> threads = new ArrayList<ThreadPool.PoolThread>();

	public ThreadPool(int numberOfThreads, int numberOfMaxTask) {

		taskQueue = new LinkedBlockingQueue<Runnable>(numberOfMaxTask);

		for (int i = 0; i < numberOfThreads; i++) {

			threads.add(new PoolThread(poolGroup, "th-" + i, taskQueue));
		}

		for (PoolThread th : threads) {

			th.start();
		}
	}

	public synchronized void execute(Runnable task) throws Exception {
		this.taskQueue.put(task);
	}

	public synchronized int countActiveThreads() {
		return poolGroup.activeCount();
	}

	public synchronized void stop() {
		poolGroup.interrupt();
	}

	private class PoolThread extends Thread {

		private BlockingQueue<Runnable> taskQueue = null;

		public PoolThread(ThreadGroup tg, String name, BlockingQueue<Runnable> queue) {
			super(tg, name);
			this.taskQueue = queue;
		}

		@Override
		public void run() {

			while (!isInterrupted()) {
				System.out.format("%s: Thread from pool is running...\n", Thread.currentThread().getName());
				try {
					Runnable runnable = taskQueue.take();
					System.out.format("%s: Task received!\n", Thread.currentThread().getName());
					runnable.run();
				} catch (Exception ex) {
					System.out.format("%s: Error occured during task execution!...\n",
							Thread.currentThread().getName());
					Thread.currentThread().interrupt();
				}
			}

		}

	}
	
	
	public static void main(String[]args) throws Exception{
		
		ThreadPool pool = new ThreadPool(5, 10);
		
		Thread.sleep(3000);
		
		
		for(int i = 0; i< 50; i++){
			

			Runnable test = new Runnable() {
				
				public void run() {
					

					System.out.println("Important task : "+UUID.randomUUID());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
			
			pool.execute(test);
		}
		
		pool.stop();
		
	}
}
