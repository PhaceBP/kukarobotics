package com.kukarobotics.di.test;

import com.kukarobotics.di.Inject;
import com.kukarobotics.di.PropertySource;

public class ServiceClient {

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
