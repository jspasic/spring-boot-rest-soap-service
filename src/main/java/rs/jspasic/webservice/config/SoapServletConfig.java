package rs.jspasic.webservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapServletConfig extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soap/*");
	}

	@Bean(name = "customerdetails")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customerDetailsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CustomerDetailsPort");
		wsdl11Definition.setLocationUri("/soap");
		wsdl11Definition.setTargetNamespace("http://jspasic.rs/soap/customerdetails");
		wsdl11Definition.setSchema(customerDetailsSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema customerDetailsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/customerDetails.xsd"));
	}
	
	
}
