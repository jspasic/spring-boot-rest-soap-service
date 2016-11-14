package rs.jspasic.webservice.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerDetailsDaoTest {

	@Autowired
	private CustomerDetailsDao customerDetailsDao;
	
	@Test
	public void customerDetailsDaoIsNotNull() {
		Assert.assertNotNull(customerDetailsDao);
	}
	

	@Test
	@Transactional
	@Rollback(true)
	public void customerDetailsCRUDTestSuccessful() {
		CustomerDetailsEntity cdeNew = new CustomerDetailsEntity();
		cdeNew.setName("Some Name");
		cdeNew.setPhoneNumber("+38912225554");
		cdeNew.setEmail("mail@example.com");
		cdeNew.setDob(new Date());
		
		CustomerDetailsEntity cdeRead = customerDetailsDao.save(cdeNew);
		
		CustomerDetailsEntity cdeRead2 = customerDetailsDao.findOneByCustomerId(cdeRead.getCustomerId());
		
		Assert.assertEquals("Some Name", cdeRead2.getName());
	}
	
}
