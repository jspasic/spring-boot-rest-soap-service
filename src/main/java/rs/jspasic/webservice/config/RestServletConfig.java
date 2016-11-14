package rs.jspasic.webservice.config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class RestServletConfig {

	@Bean(name=DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration() {
		ServletRegistrationBean srb = new ServletRegistrationBean(dispatcherServlet());
		srb.addUrlMappings("/rest/*");
		return srb;
	}
	
}
