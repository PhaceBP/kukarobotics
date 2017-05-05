package com.kukarobotics.di.test;

import com.kukarobotics.di.Inject;
import com.kukarobotics.di.PropertySource;
import com.kukarobotics.reflectiondemo.examples.BenchMarked;
import com.kukarobotics.reflectiondemo.examples.Roles;
import com.kukarobotics.reflectiondemo.examples.Secure;

@Secure(role = Roles.ADMIN)
@BenchMarked
public class ServiceClient  {

	@Inject
	private DefaultService service;

	@PropertySource(propertyName = "java.version")
	private String javaVersion;

	public DefaultService getService() {
		return service;
	}

	public void setDefaultService(DefaultService service) {
		this.service = service;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

}
