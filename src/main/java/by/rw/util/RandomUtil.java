package by.rw.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtil
{
	private static final int defaultStrSize = 10;

	public static String getRandomStr(int size)
	{
		return RandomStringUtils.randomAlphabetic(size);
	}

	public static String getRandomStr()
	{
		return getRandomStr(defaultStrSize);
	}
}
