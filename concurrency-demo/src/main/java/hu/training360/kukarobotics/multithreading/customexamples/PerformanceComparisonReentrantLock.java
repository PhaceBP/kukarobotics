package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PerformanceComparisonReentrantLock {

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

	public static void main(String[] args) {

		Counter c = new Counter(0L);
		Lock lock = new ReentrantLock();
		long startTime = System.currentTimeMillis();
		for (long i = 0; i < 1000000000L; i++) {
			lock.lock();
			try {
				c.incrementByOne();
			} finally {
				lock.unlock();
			}

		}
		long endTime = System.currentTimeMillis();

		System.out.println("Time taken : " + (endTime - startTime) + " ms");
		System.out.println("Result is : " + c.getCount());
	}
}
