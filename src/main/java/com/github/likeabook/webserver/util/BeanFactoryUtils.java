package com.github.likeabook.webserver.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;


@Component
public class BeanFactoryUtils implements BeanFactoryAware {
	public static Logger logger = Logger.getLogger(BeanFactoryUtils.class);

	private static BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		setBeanFactoryStatic(beanFactory);
	}

	private static void setBeanFactoryStatic(BeanFactory beanFactory) {
		BeanFactoryUtils.beanFactory = beanFactory;
	}

	public static <T> T getBean(Class<T> clazz) {
		return beanFactory.getBean(clazz);
	}

	public static Object getBean(String beanName) {
		return beanFactory.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		return beanFactory.getBean(beanName, clazz);
	}

}
