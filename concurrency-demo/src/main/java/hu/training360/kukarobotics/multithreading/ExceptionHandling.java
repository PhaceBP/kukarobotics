package hu.training360.kukarobotics.multithreading;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandling implements UncaughtExceptionHandler {

	public void uncaughtException(Thread t, Throwable e) {

		System.err.println("Error occured int thread : " + t.getName() + " Exception is : " + e.getMessage());

	}

	public static void main(String[] args) {

		ExceptionHandling h = new ExceptionHandling();

		Thread.setDefaultUncaughtExceptionHandler(h);

		Runnable r = new Runnable() {

			public void run() {

				try {
					Thread.sleep(3000);
					throw new IllegalArgumentException("Hahahaha!");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};

		Thread t = new Thread(r);

		t.setUncaughtExceptionHandler(h);
		t.start();

	}

}
