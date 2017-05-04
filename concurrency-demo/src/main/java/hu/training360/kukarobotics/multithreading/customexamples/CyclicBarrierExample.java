package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

	static class CyclicRunnable implements Runnable {

		private CyclicBarrier barrier1 = null;
		private CyclicBarrier barrier2 = null;

		public CyclicRunnable(CyclicBarrier b1, CyclicBarrier b2) {
			this.barrier1 = b1;
			this.barrier2 = b2;
		}

		@Override
		public void run() {

			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
				barrier1.await();
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
				barrier2.await();
				System.out.println(Thread.currentThread().getName() + " done! ");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {

		Runnable barrierAction1 = () -> {

			System.out.println("BarrierAction 1 executed!");
		};

		Runnable barrierAction2 = () -> {

			System.out.println("BarrierAction 2 executed!");
		};

		CyclicBarrier b1 = new CyclicBarrier(2, barrierAction1);
		CyclicBarrier b2 = new CyclicBarrier(2, barrierAction2);

		CyclicRunnable r1 = new CyclicRunnable(b1, b2);
		CyclicRunnable r2 = new CyclicRunnable(b1, b2);

		new Thread(r1).start();
		new Thread(r2).start();

	}
}
