package hu.training360.kukarobotics.multithreading;

public class Lab1 {

	public static void main(String[] args) throws InterruptedException {

		Runnable r = new Runnable() {

			@SuppressWarnings("static-access")
			public void run() {

				while(!Thread.interrupted()){
					try {
						Thread.currentThread().sleep(2000);
						System.out.println("Hello: " + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}


			}
		};
		
		System.out.println(Thread.currentThread().getName());
		
		Thread t2 = new Thread(r);
		
		t2.start();
		
		Thread.sleep(3000);
		
		t2.interrupt();
	}
}
