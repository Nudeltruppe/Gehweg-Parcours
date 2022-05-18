package nudeltruppe.utils;

import java.io.FileWriter;
import java.io.IOException;

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
}
