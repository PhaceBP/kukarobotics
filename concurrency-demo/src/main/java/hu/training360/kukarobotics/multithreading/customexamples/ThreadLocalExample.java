package hu.training360.kukarobotics.multithreading.customexamples;

public class ThreadLocalExample {

	public static class MyRunnable implements Runnable {

		public ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

		public MyRunnable() {

			
		}

		public MyRunnable(MyRunnable other) {

			other.getThreadLocal().set(333);
		}

		public void run() {

			System.out.println("TH VALUE BEFORE SET: "+threadLocal.get());
			
			threadLocal.set((int) (Math.random() * 100D));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(threadLocal.get());
		}

		public ThreadLocal<Integer> getThreadLocal() {
			return threadLocal;
		}

	}

	public static void main(String[] args) {

		MyRunnable r = new MyRunnable();
		MyRunnable r2 = new MyRunnable(r);

		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r2);

		th1.start();
		th2.start();
	}
}
