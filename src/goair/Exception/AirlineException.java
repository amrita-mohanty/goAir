package goair.Exception;

import goair.constants.AirlineConstants;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class AirlineException extends Exception {
	
	public static Logger logger = Logger.getLogger(AirlineException.class);

	private static ResourceBundle m_rb = null;
	protected int m_errorCode = -1;
	protected String m_message = null;
	
	public AirlineException(String message, int errorCode)
	{
		//super(message);
		m_message = message;
		m_errorCode = errorCode;

	}


	public String getMessage()
	{
		return m_message;
	}

	public void setMessage(String message)
	{
		m_message = message;
	}

	public int getErrorCode()
	{
		return m_errorCode;
	}


	/**
	 * Helper to localize exception messages.
	 */ 
	protected static String getLocalizedString(int msgCode, String args)
	{
		// Returns a prefixed message, e.g., "AMS-100: Unexpected error"
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
		m_rb = ResourceBundle.getBundle(AirlineConstants.AIRLINE_RESBUN);
		return m_rb.getString( AirlineConstants.ERROR_PREFIX + "-" + id );
	}
	
	public static AirlineException loginCredentialsIncorrect(String type) {
		
		return new AirlineException(getLocalizedString(AirlineConstants.LOGIN_CREDENTIALS_INCORRECT, type), AirlineConstants.LOGIN_CREDENTIALS_INCORRECT);
		
	}


}
