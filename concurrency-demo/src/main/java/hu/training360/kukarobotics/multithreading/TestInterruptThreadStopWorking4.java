package hu.training360.kukarobotics.multithreading;

public class TestInterruptThreadStopWorking4 {

	class TestInterruptThread extends Thread {

		@Override
		public void run() {

			while (!Thread.currentThread().isInterrupted()) {

				try {
					System.out.println("I am working... " 
				    + Thread.currentThread().getName());
				} catch (Exception ex) {
					System.err.println("Error while work.. "+ex);
					Thread.currentThread().interrupt();
				}

			}
		}
	}

	public static void main(String[] args) {

		TestInterruptThread t1 = new TestInterruptThreadStopWorking4().new TestInterruptThread();

		t1.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.err.println("Exception " + e);
		}

		t1.interrupt();

	}
}
