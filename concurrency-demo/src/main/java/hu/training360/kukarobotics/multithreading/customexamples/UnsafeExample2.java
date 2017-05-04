package hu.training360.kukarobotics.multithreading.customexamples;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeExample2 {

	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe u = (Unsafe) f.get(null);

		Secure s = new Secure();

		System.out.println(s.giveAccess());

		Field f2 = s.getClass().getDeclaredField("ACCESS_ALLOWED");

		u.putInt(s, u.objectFieldOffset(f2), 42);
		
		System.out.println(s.giveAccess());
	}
}
