package com.kukarobotics.di;

public class BeanDefinition {

	private String id;

	private String className;

	private Scope beanScope;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Scope getBeanScope() {
		return beanScope;
	}

	public void setBeanScope(Scope beanScope) {
		this.beanScope = beanScope;
	}

}
