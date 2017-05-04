package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PerformanceComparisonReentrantLockMultiThread {

	static class Counter {

		private long count;

		public Counter(long count) {
			this.count = count;
		}

		public void incrementByOne() {
			count++;
		}

		public long getCount() {
			return count;
		}
	}

	public static void main(String[] args) throws Exception {

		Counter c = new Counter(0L);
		Lock lock = new ReentrantLock();

		Runnable r = () -> {

			for (long i = 0; i < 500000000; i++) {
				lock.lock();
				try {
					c.incrementByOne();
				} finally {
					lock.unlock();
				}
			}
		};

		Thread w1 = new Thread(r);
		Thread w2 = new Thread(r);

		long startTime = System.currentTimeMillis();

		w1.start();
		w2.start();

		w1.join();
		w2.join();

		long endTime = System.currentTimeMillis();

		System.out.println("Time taken : " + (endTime - startTime) + " ms");
		System.out.println("Result is : " + c.getCount());
	}
}
