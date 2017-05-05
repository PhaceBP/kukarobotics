package com.kukarobotics.classloader.reloading;

public class App {

	
	public static void main(String[]args) throws Exception{
		
		ClassLoader parent = App.class.getClassLoader();
		
		RefreshableClassLoader cl = new RefreshableClassLoader(parent);
		
		Class<?> testClassClazz = cl.loadClass("com.kukarobotics.classloader.reloading.TestClass");
		
		ITestClass obj = (ITestClass) testClassClazz.newInstance();
		
		RefreshableClassLoader c2 = new RefreshableClassLoader(parent);
		
		Class<?> testClassClazz2 = c2.loadClass("com.kukarobotics.classloader.reloading.TestClass");
		
		ITestClass obj2 = (ITestClass) testClassClazz2.newInstance();
		
		System.out.println(obj);
		System.out.println(obj2);
		
		
	}
}
