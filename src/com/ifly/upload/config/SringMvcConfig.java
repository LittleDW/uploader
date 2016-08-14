package com.ifly.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ifly.upload.constant.Constant;

@Configuration
@EnableWebMvc
@ComponentScan(value = Constant.BASE_PACKAGE_CONTROLLER)

public class SringMvcConfig extends WebMvcConfigurerAdapter{
	
	public SringMvcConfig(){
		super();
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setViewClass(JstlView.class);
		irvr.setPrefix(Constant.VIEW_RESOLVER_PREFIX);
		irvr.setSuffix(Constant.VIEW_RESOLVER_SUFFIX);
		irvr.setOrder(1);
		return irvr;
	}
	
	//"/static/**" <--> "/static/":/static/css/style.css;static/js/jquery.js
	public void addResourceHandlers(ResourceHandlerRegistry rhr) {
		rhr.addResourceHandler(Constant.RESOURCE_HANDLER_STATIC).
			addResourceLocations(Constant.RESOURCE_LOCATION_STATIC);
		/*.setCachePeriod(3600).resourceChain(true).addResolver(new GzipResourceResolver());*/
		
	}
	
	/*@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setBasename("message");
		return rbms;
	}*/
	
}
