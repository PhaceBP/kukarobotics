package com.kukarobotics.di;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogExecutionTimeHandler implements InvocationHandler {

	private Object targetObject;

	public LogExecutionTimeHandler(Object invocationTarget) {
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
