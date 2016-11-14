package rs.jspasic.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.jspasic.webservice.dao.CustomerDetailsDao;
import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.exception.CustomerNotFoundException;
import rs.jspasic.webservice.exception.InvalidInputArgumentsException;

@Service
public class CustomerDetailsServiceOnDao implements CustomerDetailsService {

	@Autowired
	private CustomerDetailsDao customerDetailsDao;
	
	@Override
	@Transactional(readOnly=true)
	public CustomerDetailsEntity getCustomerDetails(Long customerId) {
		CustomerDetailsEntity cde = customerDetailsDao.findOneByCustomerId(customerId);
		if (cde == null) {
			throw new CustomerNotFoundException("No record found for customerId=" + customerId);
		}
		return cde;
	}

	@Override
	@Transactional
	public CustomerDetailsEntity setCustomerDetails(CustomerDetailsEntity customerDetails) {
		
		if (customerDetails.getCustomerId() != null) {
			CustomerDetailsEntity cde = customerDetailsDao.findOne(customerDetails.getCustomerId());
			if (cde == null) {
				throw new InvalidInputArgumentsException("Invalid input arguments");
			}
		}
		
		return customerDetailsDao.save(customerDetails);
	}

	public void setCustomerDetailsDao(CustomerDetailsDao customerDetailsDao) {
		this.customerDetailsDao = customerDetailsDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<CustomerDetailsEntity> getCustomerDetails() {
		return customerDetailsDao.findAll();
	}
	
	

}
