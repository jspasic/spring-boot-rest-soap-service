package rs.jspasic.webservice.service;

import java.util.List;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;

public interface CustomerDetailsService {
	
	public List<CustomerDetailsEntity> getCustomerDetails();
	
	public CustomerDetailsEntity getCustomerDetails(Long customerId);
	public CustomerDetailsEntity setCustomerDetails(CustomerDetailsEntity customerDetails);
	
}
