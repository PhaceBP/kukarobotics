package hu.training360.kukarobotics.multithreading;

public class TestInterruptThreadStopWorking3 {

	class TestInterruptThread extends Thread {

		@Override
		public void run() {

			for (int i = 0; i < 1000000; i++) {
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {

		TestInterruptThread t1 = new TestInterruptThreadStopWorking3().new TestInterruptThread();

		t1.start();

		t1.interrupt();

	}
}
