package com.sooraj.springboot.configuration.web;

import com.sooraj.springboot.configuration.persistance.PersistenceConfiguration;
import com.sooraj.springboot.configuration.security.MvcWebSecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {PersistenceConfiguration.class,WebMvcConfiguration.class, MvcWebSecurityConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
}