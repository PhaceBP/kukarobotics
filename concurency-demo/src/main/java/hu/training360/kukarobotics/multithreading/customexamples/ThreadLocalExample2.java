package hu.training360.kukarobotics.multithreading.customexamples;

public class ThreadLocalExample2 {

	
	
	private static ThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<Integer>();
	
	
	public static class MyRunnable implements Runnable {
	
		public void run() {

			System.out.println("Thread name: "+Thread.currentThread().getName()+ " / Inherited threadlocal value: "+inheritableThreadLocal.get());
			
			inheritableThreadLocal.set((int) (Math.random() * 100D));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Thread name: "+Thread.currentThread().getName()+ " / Inherited threadlocal value after modification: "+inheritableThreadLocal.get());
		}
	}

	public static void main(String[] args) {

		
		inheritableThreadLocal.set(100);
		
		MyRunnable r = new MyRunnable();
		MyRunnable r2 = new MyRunnable();

		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r2);

		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("Finally: "+inheritableThreadLocal.get());
	}
}
