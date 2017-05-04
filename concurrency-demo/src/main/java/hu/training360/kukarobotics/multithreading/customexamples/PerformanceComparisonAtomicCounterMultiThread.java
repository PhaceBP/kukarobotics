package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.concurrent.atomic.AtomicLong;

public class PerformanceComparisonAtomicCounterMultiThread {

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

	public static void main(String[] args) throws InterruptedException {

		Counter c = new Counter(new AtomicLong());
		
		Runnable r = () -> {

			for (long i = 0; i < 500000000; i++) {
				c.incrementByOne();
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
