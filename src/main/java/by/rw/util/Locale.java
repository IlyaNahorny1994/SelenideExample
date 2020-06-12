package by.rw.util;

public enum Locale
{
	RU("ru"), EN("en"), BY("by");

	private String localeText;

	Locale(String localeText)
	{
		this.localeText = localeText;
	}

	public String getLocaleText()
	{
		return localeText;
	}

	public static Locale getLocaleByText(String localeName)
	{
		for (Locale locale: values())
		{
			if (locale.getLocaleText().equals(localeName))
			{
				return locale;
			}
		}
		throw new IllegalArgumentException(String.format("Locale with name '%s' is not found.", localeName));
	}
}
