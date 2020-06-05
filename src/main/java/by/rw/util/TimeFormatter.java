package by.rw.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeFormatter
{
	private static final Pattern PATTERN_HH_MM = Pattern.compile("((\\d+)h)?((\\d+)min)?");

	public static int getMinutes(TimeFormat format, String value)
	{
		int minutes = -1;
		switch (format)
		{
			case HH_COLON_MM:
				String[] timeArray = value.split(":");
				int hh = Integer.parseInt(timeArray[0]);
				int mm = Integer.parseInt(timeArray[1]);
				minutes = hh * 60 + mm;
				break;
			case HH_MM:
				Calendar calendar = getCalendarFromHHMMStr(value);
				minutes = calendar.get(Calendar.HOUR) * 60 + calendar.get(Calendar.MINUTE);
				break;

			default:
				throw new IllegalArgumentException(String.format("Conversion for '%s' format is not implemented.", format.toString()));
		}
		return minutes;
	}

	private static Calendar getCalendarFromHHMMStr(String value)
	{
		Matcher m = PATTERN_HH_MM.matcher(value.trim().replaceAll(" ", ""));
		Calendar calendar = new GregorianCalendar();
		if (m.find())
		{
			String hh = m.group(2);
			String mm = m.group(4);
			calendar.set(Calendar.HOUR, hh != null ? Integer.parseInt(hh) : 0);
			calendar.set(Calendar.MINUTE, mm != null ? Integer.parseInt(mm) : 0);
		}
		return calendar;
	}
}