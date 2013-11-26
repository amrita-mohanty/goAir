package goair.constants;

import java.util.ListResourceBundle;

public class AirlineResourceBundle extends ListResourceBundle{

	 private static final Object[][] MESSAGE_TEXTS = 
		  {
			  { "AMS-101", "Login credentials are incorrect for this \"{0}\". Please verify again."},
			  { "AMS-102", "... add some errors..." }			  

		  };
		  
		  public Object[][] getContents()
		  {
		    return MESSAGE_TEXTS;
		  }
		}
