package rs.jspasic.webservice.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.service.CustomerDetailsService;
import rs.jspasic.webservice.soap.model.CustomerDetails;
import rs.jspasic.webservice.soap.model.GetCustomerDetailsRequest;
import rs.jspasic.webservice.soap.model.GetCustomerDetailsResponse;
import rs.jspasic.webservice.soap.model.SetCustomerDetailsRequest;
import rs.jspasic.webservice.soap.model.SetCustomerDetailsResponse;
import rs.jspasic.webservice.util.CustomerDetailsMapper;

@Endpoint
public class CustomerDetailsEndpoint {
	
	private static final String NAMESPACE_URI = "http://jspasic.rs/soap/customerdetails";
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="getCustomerDetailsRequest")
	@ResponsePayload
	public GetCustomerDetailsResponse getCustomerDetails(@RequestPayload GetCustomerDetailsRequest request) {
		GetCustomerDetailsResponse response = new GetCustomerDetailsResponse();
		CustomerDetailsEntity cde = customerDetailsService.getCustomerDetails(request.getCustomerId());
		CustomerDetails customerDetails = CustomerDetailsMapper.entityToSoapDto(cde);
		response.setCustomerDetails(customerDetails);
		return response;
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="setCustomerDetailsRequest")
	@ResponsePayload
	public SetCustomerDetailsResponse setCustomerDetails(@RequestPayload SetCustomerDetailsRequest request) {
		SetCustomerDetailsResponse response = new SetCustomerDetailsResponse();
		CustomerDetailsEntity cde = CustomerDetailsMapper.soapDtoToEntity(request.getCustomerDetails());
		CustomerDetailsEntity persistedCde = customerDetailsService.setCustomerDetails(cde);
		CustomerDetails cd = CustomerDetailsMapper.entityToSoapDto(persistedCde);
		response.setCustomerDetails(cd);
		return response;
	}

	
	
	
	public void setCustomerDetailsService(CustomerDetailsService customerDetailsService) {
		this.customerDetailsService = customerDetailsService;
	}
	
	
}
