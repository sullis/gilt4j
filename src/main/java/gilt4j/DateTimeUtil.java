
package gilt4j;

import java.util.*;
import java.text.*;

class DateTimeUtil
{
	// related:  http://stackoverflow.com/questions/424522/how-can-i-recognize-the-zulu-time-zone-in-java-dateutils-parsedate
	public static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	static public String toString(java.util.Calendar c)
	{
		return toString(c.getTime());
	}
	
	private static SimpleDateFormat getDateFormat() {
		// TODO : use a ThreadLocal
		SimpleDateFormat fmt = new SimpleDateFormat(PATTERN);
		fmt.setTimeZone(TimeZone.getTimeZone("Zulu"));
		return fmt;
	}
	
	static public String toString(java.util.Date d)
	{
		return getDateFormat().format(d);
	}
	
	static public Calendar toCalendar(String dateTimeString) throws ParseException
	{
		Date d = getDateFormat().parse(dateTimeString);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		return c;
	}
	
	static public Date toDate(String dateTimeString) throws ParseException {
		return toCalendar(dateTimeString).getTime();
	}
}
