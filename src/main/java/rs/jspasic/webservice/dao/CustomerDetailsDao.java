package rs.jspasic.webservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;

@Repository
public interface CustomerDetailsDao extends JpaRepository<CustomerDetailsEntity, Long> {
	
	public List<CustomerDetailsEntity> findAll();
	public CustomerDetailsEntity findOneByCustomerId(Long customerId);
	
}
