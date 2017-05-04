package hu.training360.kukarobotics.multithreading;

public class TestInterruptThreadStopWorking {

	class TestInterruptThread extends Thread {

		@Override
		public void run() {

			try {
				Thread.sleep(1000);
				System.out.println("Working....");
			} catch (InterruptedException e) {
				throw new RuntimeException("Thread interrupted ==> " + e);
			}
		}
	}

	
	public static void main(String[]args){
		
		TestInterruptThread t1 = new TestInterruptThreadStopWorking().new TestInterruptThread();
		
		t1.start();
		
		try{
			t1.interrupt();
		}
		catch(Exception e){
			System.err.println("Exception handled..."+e);
		}
		
	}
}
