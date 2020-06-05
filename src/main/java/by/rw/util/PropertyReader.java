package by.rw.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader
{
	public static Properties readPropertiesFile(String fileName)
	{
		FileInputStream fis = null;
		Properties prop = null;
		try
		{
			fis = new FileInputStream(getAbsolutePath(fileName));
			prop = new Properties();
			prop.load(fis);
		}
		catch (IOException fnfe)
		{
			fnfe.printStackTrace();
		}
		finally
		{
			try
			{
				assert fis != null;
				fis.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return prop;
	}

	public static String getAbsolutePath(String fileName)
	{
		URL res = PropertyReader.class.getClassLoader().getResource(fileName);
		File file = null;
		try
		{
			file = Paths.get(res.toURI()).toFile();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
}
