package hu.training360.kukarobotics.multithreading;

public class TestInterruptThreadStopWorking2 {

	class TestInterruptThread extends Thread {

		@Override
		public void run() {

			for (int i = 0; i < 2; i++) {

				if (Thread.interrupted()) {
					System.out.println(Thread.currentThread().getName() + " interrupted!");
				} else {
					System.out.println(Thread.currentThread().getName() + " working!");
				}
			}
		}
	}

	public static void main(String[] args) {

		TestInterruptThread t1 = new TestInterruptThreadStopWorking2().new TestInterruptThread();
		t1.setName("t1");
		TestInterruptThread t2 = new TestInterruptThreadStopWorking2().new TestInterruptThread();
		t2.setName("t2");

		t1.start();

		t1.interrupt();

		t2.start();

	}
}
