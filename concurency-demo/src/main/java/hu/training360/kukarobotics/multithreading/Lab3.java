package hu.training360.kukarobotics.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Lab3 {

	
	private static class Producer implements Runnable {

		private final List<Integer> taskQueue;
		private final int MAX_CAPACITY;

		private Producer(List<Integer> sharedQue, int size) {
			this.taskQueue = sharedQue;
			this.MAX_CAPACITY = size;
		}

		public void run() {

			int counter = 0;

			while (true) {

				try {
					produce(counter++);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		private void produce(int i) throws InterruptedException {

			synchronized (taskQueue) {

				while (taskQueue.size() == MAX_CAPACITY) {

					System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, size : "
							+ taskQueue.size());
					taskQueue.wait();
				}

				Thread.sleep(1000);
				taskQueue.add(i);
				System.out.println("Produced: " + i);
				taskQueue.notifyAll();

			}
		}

	}

	private static class Consumer implements Runnable {

		private final List<Integer> taskQueue;

		private Consumer(List<Integer> sharedQue) {
			this.taskQueue = sharedQue;
		}

		public void run() {

			while (true) {

				try {
					consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		private void consume() throws InterruptedException {

			synchronized (taskQueue) {

				while (taskQueue.isEmpty()) {

					System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, size : "
							+ taskQueue.size());
					taskQueue.wait();
				}

				Thread.sleep(1000);
				int i = taskQueue.remove(0);
				System.out.println("Consumed: " + i + "/ " + Thread.currentThread().getName());
				taskQueue.notifyAll();

			}
		}

	}

	public static void main(String[] args) {

		List<Integer> taskQueue = new ArrayList<Integer>();
		int MAX_CAPACITY = 5;
		Thread producer = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
		Thread consumer = new Thread(new Consumer(taskQueue), "Consumer");
		consumer.setPriority(8);
		Thread consumer2 = new Thread(new Consumer(taskQueue), "Consumer2");
		consumer2.setPriority(5);

		producer.start();
		consumer.start();
		consumer2.start();
	}

}
