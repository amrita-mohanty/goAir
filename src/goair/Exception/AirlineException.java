package goair.Exception;

import goair.constants.AirlineConstants;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.xml.ws.WebFault;

import org.apache.log4j.Logger;

@WebFault
public class AirlineException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger(AirlineException.class);

	protected int m_errorCode = -1;
	protected String m_errorMsg = "";
	
	public AirlineException() {}
	
	public AirlineException(String errormessage, int errorCode)
	{
		super(errormessage);
		m_errorMsg = errormessage;
		m_errorCode = errorCode;

	}

	public String getMessage()
	{
		return m_errorMsg;
	}

	public void setMessage(String errormessage)
	{
		m_errorMsg = errormessage;
	}

	public int getErrorCode()
	{
		return m_errorCode;
	}


	/**
	 * Helper to localize exception errormessages.
	 */ 
	public static String getLocalizedString(int msgCode, String args)
	{
		// Returns a prefixed errormessage, e.g., "AMS-100: Unexpected error"
		return AirlineConstants.ERROR_PREFIX + "-" + msgCode + ": " + getMessage( msgCode, args );
	}

	public static String getMessage(int id, String arg1) 
	{
		return MessageFormat.format( getMessage( id ), new Object []{ arg1 } );
	}

	public static String getMessage (int id, String arg1, String arg2) 
	{
		return MessageFormat.format(
				getMessage( id ), new Object []{ arg1, arg2 } ); 
	}

	public static String getMessage(int id, String arg1, String arg2, String arg3) 
	{
		return MessageFormat.format(
				getMessage( id ), new Object []{ arg1, arg2, arg3 } ); 
	}

	public static String getMessage(int id)
	{
		ResourceBundle m_rb = ResourceBundle.getBundle(AirlineConstants.AIRLINE_RESBUN);
		return m_rb.getString( AirlineConstants.ERROR_PREFIX + "-" + id );
	}
	
	public static AirlineException loginCredentialsIncorrect(String type) {
		
		return new AirlineException(getLocalizedString(AirlineConstants.LOGIN_CREDENTIALS_INCORRECT, type), AirlineConstants.LOGIN_CREDENTIALS_INCORRECT);
		
	}


}
