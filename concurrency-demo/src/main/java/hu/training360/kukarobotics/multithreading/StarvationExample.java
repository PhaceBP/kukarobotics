package hu.training360.kukarobotics.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class StarvationExample {

	private static Object mutex = new Object();

	static class Worker extends Thread {

		private AtomicInteger counter = new AtomicInteger();

		public Worker(ThreadGroup tg, String name) {
			super(tg, name);
		}

		@Override
		public void run() {

			while (!isInterrupted()) {

				synchronized (mutex) {
					System.out.format("%s: Current run count is %d...\n", Thread.currentThread().getName(),counter.incrementAndGet());
				}
			}
			System.out.format("FINISHED ==> %s: Current run count is %d...\n", Thread.currentThread().getName(),
					counter.get());
		}
	}

	public static void main(String[] args) {

		ThreadGroup tg = new ThreadGroup("KUKA Thread group");
		
		tg.setMaxPriority(10);

		Worker t1 = new Worker(tg, "t1_p10");
		t1.setPriority(Thread.MAX_PRIORITY);

		Worker t2 = new Worker(tg, "t2_p8");
		t2.setPriority(8);

		Worker t3 = new Worker(tg, "t3_p6");
		t3.setPriority(6);

		Worker t4 = new Worker(tg, "t4_p4");
		t4.setPriority(4);

		Worker t5 = new Worker(tg, "t5_p2");
		t5.setPriority(2);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tg.interrupt();

	}

}
