package hu.training360.kukarobotics.multithreading;

public class Lab2 {

	public static Object lock1 = new Object();
	public static Object lock2 = new Object();


	private static class Thread1 extends Thread {

		@Override
		public void run() {

			synchronized (lock1) {

				System.out.println("Thread 1: holding lock 1...");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Thread 1: waiting for lock 2...");

				synchronized (lock2) {
					System.out.println("Thread 1: holding lock 1 & 2...");
				}
			}
		}

	}

	private static class Thread2 extends Thread {

		@Override
		public void run() {

			synchronized (lock2) {

				System.out.println("Thread 2: holding lock 2...");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Thread 2: waiting for lock 1...");

				synchronized (lock1) {
					System.out.println("Thread 2: holding lock 1 & 2...");
				}
			}
		}

	}
	
	public static void main(String[]args){
		
		Thread1 th1 = new Thread1();
		Thread2 th2 = new Thread2();
		
		th1.start();
		th2.start();
	}
}
