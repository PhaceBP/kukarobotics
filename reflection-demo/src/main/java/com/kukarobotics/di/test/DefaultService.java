package com.kukarobotics.di.test;

import com.kukarobotics.di.PropertySource;
import com.kukarobotics.reflectiondemo.examples.BenchMarked;
import com.kukarobotics.reflectiondemo.examples.Roles;
import com.kukarobotics.reflectiondemo.examples.Secure;

@Secure(role = Roles.ADMIN)
@BenchMarked
public class DefaultService implements Service {

	@PropertySource(propertyName = "os.name")
	private String osName;

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

}
