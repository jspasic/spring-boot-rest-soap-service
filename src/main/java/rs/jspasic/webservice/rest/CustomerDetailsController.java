package rs.jspasic.webservice.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.service.CustomerDetailsService;

@RestController
public class CustomerDetailsController {
	
	@Autowired
	private CustomerDetailsService customerDetailsService;

	@RequestMapping(value = "/getcustomerdetails", method = RequestMethod.GET)
	public List<Long> getCustomerIds() {
		List<CustomerDetailsEntity> customers = customerDetailsService.getCustomerDetails();
		List<Long> ids = customers.stream()
				.map(c -> c.getCustomerId())
				.collect(Collectors.toList());
		return ids;
	}

	@RequestMapping(value = "/getcustomerdetails/{customerId}", method = RequestMethod.GET)
	public CustomerDetailsEntity getCustomerDetailsForOneCustomer(@PathVariable(value="customerId") Long customerId) {
		CustomerDetailsEntity cd = customerDetailsService.getCustomerDetails(customerId);
		if (cd != null) {
			return cd;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/setcustomerdetails", method = RequestMethod.POST, 
					consumes = "application/json", produces = "application/json")
	public CustomerDetailsEntity setCustomerDetails(@RequestBody CustomerDetailsEntity customerDetails) {
		
		System.out.println(customerDetails);
		
		CustomerDetailsEntity cde = customerDetailsService.setCustomerDetails(customerDetails);
		
		return cde;
	}
	
}
