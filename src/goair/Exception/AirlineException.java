package goair.Exception;

import goair.constants.AirlineConstants;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class AirlineException extends Exception {

	private static final long serialVersionUID = 1L;
	private ResourceBundle m_rb = null;
	private Locale m_locale = Locale.getDefault();

	public AirlineException() {
		setup( m_locale );
	}

	public AirlineException(Locale locale)
	{
		setup( locale );
	}

	public void setLocale(Locale locale)
	{
		setup( locale );
	}

	public Locale getLocale()
	{
		return m_locale;  
	}

	protected int m_errorCode = -1;
	protected String m_message = null;

	// Used for exceptions deserialized from SOAPFaultExceptions
	protected String m_causeMessage = null;
	protected StackTraceElement[] m_causeStackTrace = null;

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
	 * Internal only.
	 */
	public void setCauseMessage(String causeMessage)
	{
		m_causeMessage = causeMessage;
	}

	public String getCauseMessage()
	{
		if ( getCause() != null )
			return getCause().getMessage();
		else
			return m_causeMessage;
	}

	/**
	 * Internal only.
	 */
	public void setCauseStackTrace(StackTraceElement[] causeStackTrace)
	{
		m_causeStackTrace = causeStackTrace;
	}

	public StackTraceElement[] getCauseStackTrace()
	{
		if ( getCause() != null )
			return getCause().getStackTrace();
		else
			return m_causeStackTrace;
	}


	/**
	 * Helper to localize exception messages.
	 */ 
	protected String getLocalizedString(int msgCode, 
			Object[] args, 
			Locale locale)
	{
		// Returns a prefixed message, e.g., "AMS-100: Unexpected error"
		return AirlineConstants.ERROR_PREFIX + "-" + msgCode + ": " + getMessage( msgCode, args );
	}

	public String getMessage(int id, Object arg1) 
	{
		return MessageFormat.format( getMessage( id ), new Object[]{ arg1 } );
	}

	public String getMessage (int id, Object arg1, Object arg2) 
	{
		return MessageFormat.format(
				getMessage( id ), new Object[]{ arg1, arg2 } ); 
	}

	public String getMessage(int id, Object arg1, Object arg2, Object arg3) 
	{
		return MessageFormat.format(
				getMessage( id ), new Object[]{ arg1, arg2, arg3 } ); 
	}

	public String getMessage(int id)
	{
		return m_rb.getString( AirlineConstants.ERROR_PREFIX + "-" + id );
	}

	private void setup(Locale locale)
	{
		try 
		{
			m_rb = ResourceBundle.getBundle(
					AirlineConstants.AIRLINE_RESBUN, locale
					);

			m_locale = m_rb.getLocale();
		}
		catch (Exception ignored) {} // shouldn't happen
	}


}
