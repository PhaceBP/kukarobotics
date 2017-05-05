package com.kukarobotics.di.test;

import com.kukarobotics.di.PropertySource;

public class DefaultService {

	@PropertySource(propertyName = "os.name")
	private String osName;

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

}
