package com.ifly.upload.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ifly.upload.constant.Constant;

//public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
public class MainWebAppInitializer implements WebApplicationInitializer {
	
	//set upload parameter
	private MultipartConfigElement getMultipartConfigElement(){
		return new MultipartConfigElement(null,Constant.MAX_FILE_SIZE,
			Constant.MAX_REQUEST_SIZE,Constant.FILE_SIZE_THRESHOLD);
	}
	
	/*
		<servlet>
			<servlet-name>dispatcher</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>/WEB-INF/spring-dispatcher.xml</param-value>
			</init-param>
			<load-on-startup>1</load-on-startup>
		</servlet>
		<servlet-mapping>
			<servlet-name>dispatcher</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
	*/
	public void onStartup(ServletContext sc) throws ServletException {
		AnnotationConfigWebApplicationContext acwac = 
			new AnnotationConfigWebApplicationContext();
	
		//normal application context registration,nothing matters in this case
		sc.addListener(new ContextLoaderListener(acwac));
		
		//mvc registration
		acwac.register(SringMvcConfig.class);

		acwac.scan(Constant.BASE_PACKAGE_SPRINGMVC_CONFIG);
		
		/*acwac.refresh();
		acwac.setServletContext(sc);*/
			
		ServletRegistration.Dynamic srd = sc.addServlet(Constant.
			SPRING_SERVLET_NAME,new DispatcherServlet(new GenericWebApplicationContext()));
			
		srd.addMapping("/");
		srd.setLoadOnStartup(1);
		srd.setMultipartConfig(getMultipartConfigElement());
		
	}

}
