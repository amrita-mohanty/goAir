package goair.util;

import goair.wsdl.CustomerServices;

import org.apache.log4j.Logger;

public class CheckValidity {
	public static Logger logger = Logger.getLogger(CheckValidity.class);

	// validate zipcode ... Correct pattern is 93456 or 93456-4568
	public static boolean isZipValid(String zip) {
		boolean retval = false;
		String zipCodePattern = "\\d{5}(-\\d{4})?";
		retval = zip.matches(zipCodePattern);

		logger.info("Is Valid Zipcode ? ::: " + retval);
		return retval;
	}

	//Check if the credit card number is valid or not ... has to be a 16-digits number only
	public static boolean isCreditCardValid(String creditCardcString){
		boolean isValid = false;
		String creditCardPattern = "\\d{16}?";
		isValid = creditCardcString.matches(creditCardPattern);
		logger.info("Is Valid creditCardcString ? ::: " + isValid);

		return isValid;
	}

	//Check if the employee-id is valid or not ... Has to be in SSN pattern: 345-45-2345
	public static boolean isEmployeeIdValid(String employeeId){
		boolean isValid = false;
		String employeeIdPattern = "\\d{3}-\\d{2}-\\d{4}";
		isValid = employeeId.matches(employeeIdPattern);
		logger.info("Is Valid Employee-Id ? ::: " + isValid);

		return isValid;
	}

}
