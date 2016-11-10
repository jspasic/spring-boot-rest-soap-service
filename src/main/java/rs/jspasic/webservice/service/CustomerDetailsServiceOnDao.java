package rs.jspasic.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.jspasic.webservice.dao.CustomerDetailsDao;
import rs.jspasic.webservice.entity.CustomerDetailsEntity;

@Service
public class CustomerDetailsServiceOnDao implements CustomerDetailsService {

	@Autowired
	private CustomerDetailsDao customerDetailsDao;
	
	@Override
	@Transactional(readOnly=true)
	public CustomerDetailsEntity getCustomerDetails(Long customerId) {
		return customerDetailsDao.findOneByCustomerId(customerId);
	}

	@Override
	@Transactional
	public CustomerDetailsEntity setCustomerDetails(CustomerDetailsEntity customerDetails) {
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
