package util;

import java.io.File;

public class FileChecker 
{
	public static boolean exists(String filename)
	{
		return new File(filename).exists();
	}
}
