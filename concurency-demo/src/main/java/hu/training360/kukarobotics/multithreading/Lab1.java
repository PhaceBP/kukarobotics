package hu.training360.kukarobotics.multithreading;

public class Lab1 {

	class AttributeHolder {

		private String a = "initial value";

		private  int b;
		
		private volatile boolean isAllowed;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public boolean isAllowed() {
			return isAllowed;
		}

		public void setAllowed(boolean isAllowed) {
			this.isAllowed = isAllowed;
		}

		@Override
		public String toString() {
			return "AttributeHolder [a=" + a + ", b=" + b + ", isAllowed=" + isAllowed + "]";
		}

	}

	class Task implements Runnable {

		private AttributeHolder attributeHolder;

		public Task(AttributeHolder holder) {
			this.attributeHolder = holder;
		}

		public void run() {

			System.out.println("In "+Thread.currentThread().getName());
			attributeHolder.setA("Modified A");
			attributeHolder.setAllowed(true);
			attributeHolder.setB(1);
		}

	}
	
	class Reader implements Runnable {
		
		private AttributeHolder attributeHolder;

		public Reader(AttributeHolder holder) {
			this.attributeHolder = holder;
		}

		public void run() {
			System.out.println("In "+Thread.currentThread().getName());
			System.out.println("AttributeHolder in reader : "+attributeHolder);
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Lab1 lab1 = new Lab1();
		
		AttributeHolder holder = lab1.new AttributeHolder();
		
		System.out.println("AttributeHolder before modification : "+holder);
		
		Thread modifierThread = new Thread(lab1.new Task(holder),"modifierThread");
		
		modifierThread.start();
		
		Thread readerThread = new Thread(lab1.new Reader(holder),"readerThread");
		
		readerThread.start();
		
		
	}
}
