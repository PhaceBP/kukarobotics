package hu.training360.kukarobotics.multithreading;

public class TestThreadGroup {

	class Worker extends Thread {

		public Worker(ThreadGroup tg, String name) {
			super(tg, name);

		}

		@Override
		public void run() {

			System.out.println(Thread.currentThread().getName() + " starting.");

			while (!Thread.currentThread().isInterrupted()) {

				try {
					System.out.print(Thread.currentThread().getName()+"|");
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					System.err.println(Thread.currentThread().getName() + " interrupted.");
					Thread.currentThread().interrupt();
				}

			}

		}
	}

	public static void main(String[] args) throws InterruptedException {

		TestThreadGroup test = new TestThreadGroup();

		ThreadGroup tg = new ThreadGroup("Kuka group");

		Worker w1 = test.new Worker(tg, "w1");
		Worker w2 = test.new Worker(tg, "w2");
		Worker w3 = test.new Worker(tg, "w3");

		w1.start();
		w2.start();
		w3.start();

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
