package com.kukarobotics.di.test;

import static com.kukarobotics.di.KukaBeanFactory.registerBean;

import com.kukarobotics.di.KukaBeanFactory;
import com.kukarobotics.reflectiondemo.examples.Roles;

public class App {

	public static ThreadLocal<Roles> CURRENT_USER_ROLE_HOLDER = new ThreadLocal<Roles>();

	static {
		CURRENT_USER_ROLE_HOLDER.set(Roles.USER);
	}

	public static void main(String[] args) {

		registerBean("com.kukarobotics.di.test.DefaultService");

		registerBean("com.kukarobotics.di.test.ServiceClient");

		ServiceClient client = (ServiceClient) KukaBeanFactory.getBean("ServiceClient");

		System.out.println(client);
		
		client.getService().getOsName();

	}
}
