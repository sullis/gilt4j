package gilt4j;

import java.lang.reflect.Type;
import java.util.Calendar;

import com.google.gson.*;

public class GsonFactory {
	
	public static Gson create()
	{
		CalendarAdapter calAdapter = new CalendarAdapter();
		
		GsonBuilder builder = new GsonBuilder();
		return builder.setPrettyPrinting()
						.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
						.registerTypeHierarchyAdapter(java.util.Calendar.class, calAdapter)
						.create();
	}
	
	private  static class CalendarAdapter
		implements JsonSerializer<java.util.Calendar>, JsonDeserializer<java.util.Calendar>
{

	public JsonElement serialize(java.util.Calendar c, Type arg1,
			JsonSerializationContext arg2)
	{
		JsonElement element = new JsonPrimitive(DateTimeUtil.toString(c));
		return element;
	}

	public Calendar deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException
	{
		String s = arg0.getAsString();
		
		try
		{
			Calendar c = DateTimeUtil.toCalendar(s);
			return c;
		}
		catch (java.text.ParseException ex)
		{
			throw new JsonParseException(s, ex);
		}
	}
	
}
}
