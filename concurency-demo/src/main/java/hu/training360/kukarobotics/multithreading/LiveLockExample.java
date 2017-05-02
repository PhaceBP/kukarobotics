package hu.training360.kukarobotics.multithreading;

import java.util.LinkedList;

public class LiveLockExample {

	public static void main(String[] args) {

		LinkedList<Equation> queue = new LinkedList<LiveLockExample.Equation>();

		queue.add(new Equation(100, 5));
		queue.add(new Equation(120, 6));
		queue.add(new Equation(101, 3));
		queue.add(new Equation(1024, 62));
		queue.add(new Equation(125878, 50));
		queue.add(new Equation(72, 8));
		queue.add(new Equation(198, 0)); // Dividy by zero
		queue.add(new Equation(123, 23));
		queue.add(new Equation(154, 6));
		queue.add(new Equation(64, 8));

		Thread t1 = new Thread(new Worker(queue), "t1");
		Thread t2 = new Thread(new Worker(queue), "t2");
		Thread t3 = new Thread(new Worker(queue), "t3");
		Thread t4 = new Thread(new Worker(queue), "t4");
		Thread t5 = new Thread(new Worker(queue), "t5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	static class Worker implements Runnable {

		LinkedList<Equation> queue = null;

		public Worker(LinkedList<Equation> queue) {
			this.queue = queue;
		}

		public void run() {

			while (true) {

				synchronized (queue) {

					System.out.format("%s Check elements in the queue...\n", Thread.currentThread().getName());

					try {
						if (queue.size() > 0) {

							Equation eq = queue.remove(0);

							try {
								double result = (eq.getDividend() / eq.getDivisor());
								System.out.format("%s: Equation %d / %d = %f\n", Thread.currentThread().getName(),
										eq.getDividend(), eq.getDivisor(), result);
							} catch (ArithmeticException ex) {
								ex.printStackTrace();
								queue.addFirst(eq);
							}
							queue.notify();
						}
						Thread.sleep(1000);
						queue.wait();
					} catch (Exception ex) {
						System.out.format("%s was interrupted...\n", Thread.currentThread().getName());
					}

				}
			}

		}

	}

	static class Equation {

		private int dividend;
		private int divisor;

		public Equation(int dividend, int divisor) {

			this.dividend = dividend;
			this.divisor = divisor;
		}

		public int getDividend() {
			return dividend;
		}

		public int getDivisor() {
			return divisor;
		}
	}

}
