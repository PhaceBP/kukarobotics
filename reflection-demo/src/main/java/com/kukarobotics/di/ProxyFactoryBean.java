package com.kukarobotics.di;

import java.lang.reflect.Proxy;

public class ProxyFactoryBean {

	public static Object createSecurityProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new SecurityInvocationHandler(target));
	}

	public static Object createBenchMarkProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new LogExecutionTimeHandler(target));
	}
}
