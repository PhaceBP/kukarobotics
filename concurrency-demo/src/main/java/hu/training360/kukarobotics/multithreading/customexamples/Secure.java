package hu.training360.kukarobotics.multithreading.customexamples;

public class Secure {

	private int ACCESS_ALLOWED = 1;

	public boolean giveAccess() {
		return 42 == ACCESS_ALLOWED;
	}
}
