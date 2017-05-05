package com.kukarobotics.classloader;

public class ClassLoaderTestApp {

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		System.out.println("Class loader for HashMap: " + java.util.HashMap.class.getClassLoader());

		System.out.println("Class loader for DNSNameService: "
				+ sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());

		System.out.println("Class loader for this class: " + ClassLoaderTestApp.class.getClassLoader());
		
		
		ClassLoader cl = ClassLoaderTestApp.class.getClassLoader();
		
		Class<?> clazz = cl.loadClass("com.kukarobotics.classloader.DynamicClassLoadingTest");
		
		DynamicClassLoadingTest obj = (DynamicClassLoadingTest) clazz.newInstance();
		
		System.out.println(obj);
	}
}
