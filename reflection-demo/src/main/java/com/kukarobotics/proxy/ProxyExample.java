package com.kukarobotics.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyExample {

	static interface Service {
		void echoMessage(String message);
	}

	static class DefaultService implements Service {

		public void echoMessage(String message) {
			System.out.println("Message received: " + message);
		}
	}

	static class LogExecutionTimeProxyHandler implements InvocationHandler {

		private Object targetObject;

		public LogExecutionTimeProxyHandler(Object invocationTarget) {
			this.targetObject = invocationTarget;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			long startTime = System.nanoTime();

			Object result = method.invoke(targetObject, args);

			System.out.println(
					"Executed method: " + method.getName() + " in " + (System.nanoTime() - startTime) + " nanoseconds");

			return result;
		}

	}

	public static void main(String[] args) {

		Service s = new DefaultService();

		Service serviceProxy = (Service) Proxy.newProxyInstance(ProxyExample.class.getClassLoader(),
				s.getClass().getInterfaces(), new LogExecutionTimeProxyHandler(s));
		
		
		serviceProxy.echoMessage("Hello world!");
	}
}
