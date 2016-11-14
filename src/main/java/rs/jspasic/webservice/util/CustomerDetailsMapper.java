package rs.jspasic.webservice.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.exception.GenericWebServiceException;
import rs.jspasic.webservice.soap.model.CustomerDetails;

public interface CustomerDetailsMapper {
	
	static CustomerDetailsEntity soapDtoToEntity(CustomerDetails customerDetails) {
		CustomerDetailsEntity cde = new CustomerDetailsEntity();
		cde.setCustomerId(customerDetails.getCustomerId());
		cde.setName(customerDetails.getName());
		cde.setPhoneNumber(customerDetails.getPhoneNumber());
		cde.setEmail(customerDetails.getEmail());
		Date dob = customerDetails.getDob().toGregorianCalendar().getTime();
		cde.setDob(dob);
		return cde;
	}
	
	static CustomerDetails entityToSoapDto(CustomerDetailsEntity cde) {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId(cde.getCustomerId());
		customerDetails.setName(cde.getName());
		customerDetails.setPhoneNumber(cde.getPhoneNumber());
		customerDetails.setEmail(cde.getEmail());
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(cde.getDob());
		XMLGregorianCalendar xmlDate = null; 
		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException dce) {
			if (xmlDate == null) {
				throw new GenericWebServiceException("Date conversion error");
			}
		}
		customerDetails.setDob(xmlDate);
		return customerDetails;
	}
	
}
