package hu.training360.kukarobotics.multithreading;

public class Lab4 {

	private static Object lock = new Object();

	public static void main(String[] args) {

		Thread highPrio = new Thread(new Runnable() {

			public void run() {

				synchronized (lock) {

					System.out.println("High prio thread...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		Thread lowPrio = new Thread(new Runnable() {

			public void run() {

				synchronized (lock) {

					System.out.println("Low prio thread...");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});

		highPrio.setPriority(Thread.MAX_PRIORITY);

		lowPrio.setPriority(Thread.MIN_PRIORITY);

		highPrio.start();

		lowPrio.start();

	}
}
