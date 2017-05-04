package hu.training360.kukarobotics.multithreading.customexamples;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class UnsafeExample {

	

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

		UnsafeA a1 = new UnsafeA();
		
		System.out.println(a1.getA()); //1
		
		UnsafeA a2 = UnsafeA.class.newInstance();
		
		System.out.println(a2.getA()); // 1
		
		// -Xbootclasspath:locationOfRtJar

		
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe u = (Unsafe) f.get(null);
		
		UnsafeA a3 = (UnsafeA) u.allocateInstance(UnsafeA.class);
		
		System.out.println(a3.getA());
	}
}
