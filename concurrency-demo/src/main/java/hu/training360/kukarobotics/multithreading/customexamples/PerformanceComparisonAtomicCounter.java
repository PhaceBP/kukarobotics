package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.atomic.AtomicLong;

public class PerformanceComparisonAtomicCounter {

	static class Counter {

		private AtomicLong count;

		public Counter(AtomicLong count) {
			this.count = count;
		}

		public void incrementByOne() {
			count.incrementAndGet();
		}

		public long getCount() {
			return count.get();
		}
	}

	public static void main(String[] args) {

		Counter c = new Counter(new AtomicLong());
		long startTime = System.currentTimeMillis();
		for (long i = 0; i < 1000000000L; i++) {
			c.incrementByOne();
		}
		long endTime = System.currentTimeMillis();

		System.out.println("Time taken : " + (endTime - startTime) + " ms");
		System.out.println("Result is : " + c.getCount());
	}
}
