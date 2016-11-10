package rs.jspasic.webservice.util;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;

public interface CustomerDetailsMapper {
	
	static CustomerDetailsEntity soapDtoToEntity() {
		return new CustomerDetailsEntity();
	}
	
}
