package hu.training360.kukarobotics.multithreading.customexamples;

public class Lab3 {

	static class MyRunnable implements Runnable {

		public void run() {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());

		}

	}

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new MyRunnable(), "thread1");
		t1.setPriority(1);
		t1.start();

		Thread t2 = new Thread(new MyRunnable(), "thread2");
		t2.setPriority(2);
		t2.start();
		
		Thread t4 = new Thread(new MyRunnable(), "thread4");
		t4.setPriority(4);
		t4.start();
		
		
		Thread t8 = new Thread(new MyRunnable(), "thread8");
		t8.setPriority(8);
		t8.start();
	}
}
