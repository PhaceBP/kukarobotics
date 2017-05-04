package hu.training360.kukarobotics.multithreading.customexamples;

public class Lab2 {

	static class MyRunnable implements Runnable {

		public void run() {

			for (int i = 0; i < 10; i++) {

				System.out.println(Thread.currentThread().getName() + "_" + i);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public static void main(String[] args) throws InterruptedException {

		Thread t2 = new Thread(new MyRunnable(), "thread2");

		t2.start();

		Thread t1 = new Thread(new MyRunnable(), "thread1");

		t1.start();
		
		t1.join();
		
		System.out.println("After t1....");
		
		t2.join();
		
		System.out.println("After t2....");
	}
}
