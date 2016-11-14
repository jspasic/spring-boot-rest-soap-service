package rs.jspasic.webservice.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rs.jspasic.webservice.entity.CustomerDetailsEntity;
import rs.jspasic.webservice.exception.CustomerNotFoundException;
import rs.jspasic.webservice.exception.GenericWebServiceException;
import rs.jspasic.webservice.exception.InvalidInputArgumentsException;
import rs.jspasic.webservice.service.CustomerDetailsService;

@RestController
public class CustomerDetailsController {
	
	@Autowired
	private CustomerDetailsService customerDetailsService;

	/**
	 * This method is just for convinience. It returns the customerIds for all records in the customer_details table 
	 */
	@RequestMapping(value = "/getcustomerdetails/allIds", method = RequestMethod.GET)
	public List<Long> getCustomerIds() {
		List<CustomerDetailsEntity> customers = customerDetailsService.getCustomerDetails();
		List<Long> ids = customers.stream()
				.map(c -> c.getCustomerId())
				.collect(Collectors.toList());
		return ids;
	}

	@RequestMapping(value = "/getcustomerdetails", method = RequestMethod.GET, produces = "application/json")
	public CustomerDetailsEntity getCustomerDetailsForOneCustomer(
				@RequestParam(value="customerId", required=false) Long customerId) {
		if (customerId == null) {
			throw new InvalidInputArgumentsException("Mandatory field missing");
		}
		CustomerDetailsEntity cd = null;
		try {
			cd = customerDetailsService.getCustomerDetails(customerId);
		} catch (CustomerNotFoundException cfe) {
			throw cfe;
		}
		catch (Throwable t) {
			throw new GenericWebServiceException(t.getMessage());
		}
		return cd;
	}
	
	@RequestMapping(value = "/setcustomerdetails", method = RequestMethod.POST, 
					consumes = "application/json", produces = "application/json")
	public CustomerDetailsEntity setCustomerDetails(@RequestBody CustomerDetailsEntity customerDetails) {
		if (customerDetails.getName() == null) {
			throw new InvalidInputArgumentsException("Invalid input");
		}
		
		CustomerDetailsEntity cde = null;
		try {
			cde = customerDetailsService.setCustomerDetails(customerDetails);
		} catch (CustomerNotFoundException cfe) {
			throw cfe;
		}
		catch (Throwable t) {
			throw new GenericWebServiceException(t.getMessage());
		}
		
		return cde;
	}
	

	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleCustomerNotFoundException(CustomerNotFoundException cnfe) {
		return cnfe.getMessage();
	}
	
	
	@ExceptionHandler(InvalidInputArgumentsException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleInvalidInputArguments(InvalidInputArgumentsException iiae) {
		return "Invalid input arguments";
	}
	
	@ExceptionHandler(GenericWebServiceException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleInvalidInputArguments(GenericWebServiceException gwse) {
		return "Error processing request";
	}
	
}
