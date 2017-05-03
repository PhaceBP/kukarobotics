package hu.training360.kukarobotics.multithreading.customexamples;

public class Lab4 {

	static class Worker extends Thread {

		public Worker(ThreadGroup tg, String name) {
			super(tg, name);
		}
		
		public void run() {

			while (!Thread.currentThread().isInterrupted()) {

				try {
					System.out.println("I am working... " + Thread.currentThread().getName());
					Thread.sleep(2000);
				} catch (Exception ex) {
					System.err.println("Error while work.. " + ex);
					Thread.currentThread().interrupt();
				}
			}
		}

	}
	
	
	public static void main(String[]args) throws InterruptedException{
		
		ThreadGroup tg = new ThreadGroup("main_tg");
		
		Worker w1 = new Worker(tg,"w1");
		Worker w2 = new Worker(tg,"w2");
		Worker w3 = new Worker(tg,"w3");
		Worker w4 = new Worker(tg,"w4");
		Worker w5 = new Worker(tg,"w5");
		
		w1.start();
		w2.start();
		w3.start();
		w4.start();
		w5.start();

		Thread.sleep(1000);

		System.out.println(tg.activeCount() + "threads in the thread group.");

		Thread[] threads = new Thread[tg.activeCount()];

		tg.enumerate(threads);

		for (Thread t : threads) {

			System.out.println("Thread name: " + t.getName() + " - status: " + t.getState().name());
		}

		w1.interrupt();
		
		Thread.sleep(1000);
		
		System.out.println(tg.activeCount() + "threads in the thread group after w1 interrupted.");
		
		tg.interrupt();
		
		
		
	}
}
