package goair.constants;

public class AirlineResourceBundle {

	 private static final Object[][] MESSAGE_TEXTS = 
		  {
			  { "AMS-101", "An unexpected error occurred during operation \"{0}\"."},
			  { "AMS-102", "... add some errors..." }			  

		  };
		  
		  public Object[][] getContents()
		  {
		    return MESSAGE_TEXTS;
		  }
		}
