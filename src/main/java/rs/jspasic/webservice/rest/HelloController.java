package rs.jspasic.webservice.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.jspasic.webservice.dao.CountryRepository;
import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.model.Country;
import rs.jspasic.webservice.service.CustomerDetailsService;

@RestController
public class HelloController {

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false) String name) {
		if (name != null) {
			return "Hello " + name + "!";
		} else {
			return "Hello, world!";
		}
	}
	
	@RequestMapping("/country/{name}")
	public Country country(@PathVariable(value="name") String name) {
		Country c = countryRepository.findCountry(name);
		if (c != null) {
			return c;
		} else {
			return null;
		}
	}
	
	@RequestMapping("/getcustomerdetails/{customerId}")
	public CustomerDetailsEntity getCustomerDetailsForOneCustomer(@PathVariable(value="customerId") Long customerId) {
		CustomerDetailsEntity cd = customerDetailsService.getCustomerDetails(customerId);
		if (cd != null) {
			return cd;
		} else {
			return null;
		}
	}
	
	@RequestMapping("/getcustomerdetails")
	public List<Long> getCustomerIds() {
		List<CustomerDetailsEntity> customers = customerDetailsService.getCustomerDetails();
		List<Long> ids = customers.stream()
				.map(c -> c.getCustomerId())
				.collect(Collectors.toList());
		return ids;
	}
	
}
