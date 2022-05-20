package nudeltruppe.utils;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

public class FileUtils {
	public static void writeFile(String file_name, String file_contents) throws IOException
	{
		FileWriter fw = new FileWriter(file_name);
		fw.write(file_contents);
		fw.close();
	}

	public static void appendFile(String file_name, String file_contents) throws IOException
	{
		FileWriter fw = new FileWriter(file_name, true);
		fw.write(file_contents);
		fw.close();
	}
	
	public static String readFile(String file_name) throws IOException
	{
		FileReader fr = new FileReader(file_name);
		StringWriter out = new StringWriter();

		int read;
		char[] buf = new char[4096];

		while ((read = fr.read(buf)) != -1)
		{
			out.write(buf, 0, read);
		}

		fr.close();
		return out.toString();
	}

	@Test
	public void test() throws IOException
	{
		FileUtils.writeFile("test.txt", "test");
		assertEquals("test", FileUtils.readFile("test.txt"));
	}
}
