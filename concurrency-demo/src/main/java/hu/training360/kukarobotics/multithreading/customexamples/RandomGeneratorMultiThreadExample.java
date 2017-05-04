package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RandomGeneratorMultiThreadExample {

	private List<Integer> randomNumberCache = new ArrayList<Integer>();

	public static final CountDownLatch SYNCHER = new CountDownLatch(5);

	public synchronized void addRandomNumber(Integer randomNumber) {
		randomNumberCache.add(randomNumber);
	}

	private class RandomNumberGeneratorTask implements Runnable {

		private int minValue;
		private int maxValue;

		public RandomNumberGeneratorTask(int min, int max) {
			this.minValue = min;
			this.maxValue = max;
		}

		public void run() {

			for (int i = 0; i < 10; i++) {
				int random = randomWithRange();
				System.out.println("Random number generated, thread name is: " + Thread.currentThread().getName()
						+ " value is: " + random);
				addRandomNumber(random);
			}

			SYNCHER.countDown();

		}

		private int randomWithRange() {
			int range = (maxValue - minValue) + 1;
			return (int) (Math.random() * range) + minValue;
		}

	}

	private class CacheMaintaner extends Thread {

		public CacheMaintaner(String name) {
			super(name);
		}

		@Override
		public void run() {

			try {
				SYNCHER.await();
				randomNumberCache = new ArrayList<>(new HashSet<>(randomNumberCache));
				System.out.println("Random number list clean up finished by: " + Thread.currentThread().getName());
				System.out.println("Cache content is : " + randomNumberCache);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {

		RandomGeneratorMultiThreadExample example = new RandomGeneratorMultiThreadExample();

		Thread t1 = new Thread(example.new RandomNumberGeneratorTask(1, 10), "random-gen-t1");
		Thread t2 = new Thread(example.new RandomNumberGeneratorTask(1, 10), "random-gen-t2");
		Thread t3 = new Thread(example.new RandomNumberGeneratorTask(1, 10), "random-gen-t3");
		Thread t4 = new Thread(example.new RandomNumberGeneratorTask(10, 1000), "random-gen-t4");
		Thread t5 = new Thread(example.new RandomNumberGeneratorTask(200, 1000), "random-gen-t5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		CacheMaintaner cacheMaintaner = example.new CacheMaintaner("cache-maintaner");
		cacheMaintaner.start();

	}

}
