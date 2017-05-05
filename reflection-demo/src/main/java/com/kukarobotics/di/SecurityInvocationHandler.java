package com.kukarobotics.di;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.kukarobotics.di.test.App;
import com.kukarobotics.reflectiondemo.examples.Roles;
import com.kukarobotics.reflectiondemo.examples.Secure;

public class SecurityInvocationHandler implements InvocationHandler {

	private Object targetObject;

	public SecurityInvocationHandler(Object invocationTarget) {
		this.targetObject = invocationTarget;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Secure s = method.getDeclaredAnnotation(Secure.class);

		if (s == null) {
			return method.invoke(targetObject, args);
		}

		Roles requiredRole = s.role();

		if (App.CURRENT_USER_ROLE_HOLDER.get() != requiredRole) {
			throw new SecurityException("Unsufficient privilege!");
		}

		return method.invoke(targetObject, args);
	}

}
