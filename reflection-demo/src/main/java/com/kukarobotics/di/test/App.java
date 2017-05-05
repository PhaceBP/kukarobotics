package com.kukarobotics.di.test;

import static com.kukarobotics.di.KukaBeanFactory.registerBean;

import com.kukarobotics.di.KukaBeanFactory;

public class App {

	
	public static void main(String[]args){
		
		registerBean("com.kukarobotics.di.test.DefaultService");
		
		registerBean("com.kukarobotics.di.test.ServiceClient");
		
		ServiceClient client = (ServiceClient) KukaBeanFactory.getBean("ServiceClient");
		
		
		System.out.println(client);
		
		
	}
}
