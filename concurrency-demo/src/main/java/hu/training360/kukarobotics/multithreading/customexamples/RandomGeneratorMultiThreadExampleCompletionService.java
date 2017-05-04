package hu.training360.kukarobotics.multithreading.customexamples;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RandomGeneratorMultiThreadExampleCompletionService {

	private List<Integer> randomNumberCache = new ArrayList<Integer>();

	public static final CountDownLatch SYNCHER = new CountDownLatch(6);

	public synchronized void addRandomNumber(Integer randomNumber) {
		randomNumberCache.add(randomNumber);
	}

	public synchronized void addRandomNumbers(List<Integer> randomNumbers) {
		randomNumberCache.addAll(randomNumbers);
	}

	private class RandomNumberGeneratorTask implements Callable<List<Integer>> {

		private int minValue;
		private int maxValue;
		private List<Integer> generatedNumbers = new ArrayList<>();

		public RandomNumberGeneratorTask(int min, int max) {
			this.minValue = min;
			this.maxValue = max;
		}

		public List<Integer> call() {

			for (int i = 0; i < 10; i++) {
				int random = randomWithRange();
				System.out.println("Random number generated, thread name is: " + Thread.currentThread().getName()
						+ " value is: " + random);
				generatedNumbers.add(random);
			}

			SYNCHER.countDown();

			return generatedNumbers;

		}

		private int randomWithRange() {
			int range = (maxValue - minValue) + 1;
			return (int) (Math.random() * range) + minValue;
		}

	}

	private class CacheMaintaner implements Runnable {

		@Override
		public void run() {
			try {
				System.out.println("Maintainer thread started....");
				SYNCHER.await();
				System.out.println("Generator finished their works now let's do some clean up!!!");
				randomNumberCache = new ArrayList<>(new HashSet<>(randomNumberCache));
				System.out.println("Random number list clean up finished by: " + Thread.currentThread().getName());
				System.out.println("Cache content is : " + randomNumberCache);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		RandomGeneratorMultiThreadExampleCompletionService example = new RandomGeneratorMultiThreadExampleCompletionService();

		ExecutorService es = Executors.newFixedThreadPool(6);

		es.execute(example.new CacheMaintaner());

		List<Callable<List<Integer>>> callables = new ArrayList<>();
		callables.add(example.new RandomNumberGeneratorTask(1, 10));
		callables.add(example.new RandomNumberGeneratorTask(1, 10));
		callables.add(example.new RandomNumberGeneratorTask(1, 10));
		callables.add(example.new RandomNumberGeneratorTask(10, 100));
		callables.add(example.new RandomNumberGeneratorTask(200, 1000));

		List<Future<List<Integer>>> generatedLists = es.invokeAll(callables);

		for (Future<List<Integer>> actual : generatedLists) {

			example.addRandomNumbers(actual.get());
		}

		SYNCHER.countDown();
	}

}
