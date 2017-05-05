package com.kukarobotics.di;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kukarobotics.reflectiondemo.examples.BenchMarked;
import com.kukarobotics.reflectiondemo.examples.Secure;

public class KukaBeanFactory {

	private static final Map<String, BeanDefinition> BEAN_DEF_MAP = new HashMap<String, BeanDefinition>();

	private static final Map<String, Object> SINGLETON_MAP = new HashMap<String, Object>();

	private static String generateBeanId(String className) {
		return className.substring(className.lastIndexOf('.') + 1, className.length());
	}

	public static void registerBean(String className) {
		registerBean(className, Scope.SINGLETON);
	}

	public static void registerBean(String className, Scope scope) {

		String beanId = generateBeanId(className);

		if (BEAN_DEF_MAP.containsKey(beanId)) {
			System.out.println("Bean already registered to the container {" + beanId + "}");
		}

		BeanDefinition def = new BeanDefinition();
		def.setClassName(className);
		def.setId(beanId);
		def.setBeanScope(scope);

		BEAN_DEF_MAP.put(beanId, def);
	}

	public static Object getBean(String beanId) {

		BeanDefinition def = BEAN_DEF_MAP.get(beanId);

		if (def == null) {
			throw new IllegalArgumentException("Missing bean from the container with id: " + beanId);
		}

		Object instance = null;

		if (Scope.SINGLETON == def.getBeanScope()) {

			if (SINGLETON_MAP.containsKey(beanId)) {

				instance = SINGLETON_MAP.get(beanId);
			} else {
				instance = createObject(def);
				SINGLETON_MAP.put(beanId, instance);

			}
		} else if (Scope.PROTOTYPE == def.getBeanScope()) {

			instance = createObject(def);
		}

		return instance;
	}

	private static Object createObject(BeanDefinition def) {

		Object instance = null;
		Class<?> beanClazz = null;
		try {
			beanClazz = Class.forName(def.getClassName());
			instance = beanClazz.newInstance();
			List<Field> fields = getAllField(new ArrayList<Field>(), beanClazz);

			for (Field f : fields) {
				injectDependencies(instance, f);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (beanClazz.getAnnotation(Secure.class) != null) {

			instance = ProxyFactoryBean.createSecurityProxy(instance);
		}
		if (beanClazz.getAnnotation(BenchMarked.class) != null) {

			instance = ProxyFactoryBean.createBenchMarkProxy(instance);
		}
		return instance;
	}

	private static List<Field> getAllField(List<Field> fields, Class<?> clazz) {

		List<Field> allField = new ArrayList<Field>();

		allField.addAll(Arrays.asList(clazz.getDeclaredFields()));

		if (clazz.getSuperclass() != null) {

			getAllField(allField, clazz.getSuperclass());
		}

		return allField;
	}

	private static void injectDependencies(Object containerObject, Field f) {

		if ((f.getAnnotation(Inject.class) != null) && !f.getClass().isPrimitive()) {

			Object fieldValue = null;

			try {
				fieldValue = f.getType().newInstance();
				f.setAccessible(true);
				f.set(containerObject, fieldValue);

				for (Field transitiveDependency : f.getType().getDeclaredFields()) {

					injectDependencies(fieldValue, transitiveDependency);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (f.getAnnotation(PropertySource.class) != null) {

			PropertySource annotation = f.getAnnotation(PropertySource.class);

			if (annotation.propertyName() != null) {
				f.setAccessible(true);
				try {
					f.set(containerObject, System.getProperty(annotation.propertyName()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
