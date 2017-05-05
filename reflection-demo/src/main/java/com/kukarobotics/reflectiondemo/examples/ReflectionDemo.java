package com.kukarobotics.reflectiondemo.examples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public abstract class ReflectionDemo {

	static interface Scannable {

	}

	static class ReflectionExample implements Scannable {

		private String id;
		private Date timeStamp;
		public Boolean isValid;

		public ReflectionExample(String id) {
			this.id = id;
		}

		public ReflectionExample() {
		}

		public boolean accessGranted() {
			return isValid;
		}

		public String getId() {
			return id;
		}

		@Secure(role = Roles.ADMIN)
		public void setId(String id) {
			this.id = id;
		}

		public Date getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(@Secure Date timeStamp) {
			this.timeStamp = timeStamp;
		}

		public Boolean getIsValid() {
			return isValid;
		}

		public void setIsValid(Boolean isValid) {
			this.isValid = isValid;
		}

	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		Class<?> clazz = ReflectionExample.class;

		System.out.println("FQDN of ReflectionDemo class: " + clazz.getName());

		System.out.println("Simple name of ReflectionDemo class: " + clazz.getSimpleName());

		int modifiers = clazz.getModifiers();

		System.out.println("Is abstract? : " + Modifier.isAbstract(modifiers));
		System.out.println("Is final? : " + Modifier.isFinal(modifiers));
		System.out.println("Is interface? : " + Modifier.isInterface(modifiers));
		System.out.println("Is public? : " + Modifier.isPublic(modifiers));
		System.out.println("Is protected? : " + Modifier.isProtected(modifiers));
		System.out.println("Is static? : " + Modifier.isStatic(modifiers));

		System.out.println("Declared fields of ReflectionExample class : ");

		for (Field f : clazz.getDeclaredFields()) {
			System.out.println("Name: " + f.getName() + " Type: " + f.getType());
		}

		System.out.println("Fields of ReflectionExample class : ");

		for (Field f : clazz.getFields()) {

			System.out.println("Name: " + f.getName() + " Type: " + f.getType());
		}

		System.out.println("Declared methods of ReflectionExample class : ");

		for (Method m : clazz.getDeclaredMethods()) {

			System.out.println("Name: " + m.getName() + " Type: " + m.getReturnType());
			System.out.println("Is getter? : "+isGetter(m));
			System.out.println("Is setter? : "+isSetter(m));
		}

		System.out.println("Methods of ReflectionExample class : ");

		for (Method m : clazz.getMethods()) {

			System.out.println("Name: " + m.getName() + " Type: " + m.getReturnType());
		}

		System.out.println("Constructors of ReflectionExample class : ");

		for (Constructor<?> m : clazz.getConstructors()) {

			System.out.println("Name: " + m.getName() + " Num of parameters: " + m.getParameterCount());
		}
		
		
		ReflectionExample testObject = new ReflectionExample("id1");
		
		
		try {
			Field idField = clazz.getDeclaredField("id");
			idField.setAccessible(true);
			idField.set(testObject, "id2");
			System.out.println("New id value: "+testObject.getId());
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Secure annotationObject = testObject.getClass().getAnnotation(Secure.class);
		
		System.out.println("Secure must be null ==> "+annotationObject);
		
		Method setIdMethod = testObject.getClass().getMethod("setId", String.class);
		
		annotationObject = setIdMethod.getAnnotation(Secure.class);
		
		if(annotationObject != null){
			System.out.println("This is a secure method: "+annotationObject.role());
		}
		

		Method setTimestampMethod = testObject.getClass().getMethod("setTimeStamp", Date.class);
		
		
		System.out.println("-------------------------------------------------------");
		
		Annotation[][] parameterAnnotations = setTimestampMethod.getParameterAnnotations();
		
		for(Annotation[] annotations :parameterAnnotations) {
			
			for(Annotation annotation : annotations){
				
				if(annotation instanceof Secure){
					Secure a = (Secure) annotation;
					System.out.println("Secure annotation found name is: "+a.annotationType().getName());
					System.out.println("Secure annotation found role is: "+a.role());
				}
			}
		}
	}

	public static boolean isSetter(Method m) {
		return m.getName().startsWith("set") && m.getParameterTypes().length != 0
				&& void.class.equals(m.getReturnType());
	}

	public static boolean isGetter(Method m) {
		return m.getName().startsWith("get") && m.getParameterTypes().length == 0
				&& (!void.class.equals(m.getReturnType()));
	}
}
