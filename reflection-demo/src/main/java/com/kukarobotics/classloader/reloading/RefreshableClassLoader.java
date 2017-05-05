package com.kukarobotics.classloader.reloading;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class RefreshableClassLoader extends ClassLoader {

	public RefreshableClassLoader(ClassLoader parent) {
		super(parent);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {

		Class<?> ret = null;

		if (!"com.kukarobotics.classloader.reloading.TestClass".equals(name)) {
			return super.loadClass(name);
		}

		String url = "file:D:/plugins/TestClass.class";

		try {
			URL classURL = new URL(url);
			URLConnection conn = classURL.openConnection();
			InputStream input = conn.getInputStream();

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int data = input.read();

			while (data != -1) {

				buffer.write(data);
				data = input.read();
			}

			input.close();

			byte[] classData = buffer.toByteArray();

			ret = defineClass("com.kukarobotics.classloader.reloading.TestClass", classData, 0, classData.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;

	}

}
