package com.lxq.ueditor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类
 * @author l1
 *
 */

@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtil.applicationContext == null){
            SpringUtil.applicationContext  = applicationContext;
    	}		
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
