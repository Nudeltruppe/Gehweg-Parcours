package nudeltruppe.utils;

import java.io.File;
import java.io.IOException;

public class Log {
	private static String current_log_file = "./logs/" + System.currentTimeMillis() + ".log";

	public static void log(String message)
	{
		synchronized (Log.class)
		{
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String new_message = "";
			for (String line : message.split("\n"))
			{
				new_message += String.format("[%s::%s at %s:%s] %s\n", stackTraceElements[2].getClassName(), stackTraceElements[2].getMethodName(), stackTraceElements[2].getFileName(), stackTraceElements[2].getLineNumber(), line);
			}

			// new_message = new_message.strip();

			// java 1.7 does not support String.strip()
			if (new_message.charAt(new_message.length() - 1) == '\n')
			{
				new_message = new_message.substring(0, new_message.length() - 1);
			}
			
			if (!new File("./logs").isDirectory())
			{
				new File("./logs").mkdir();
			}

			if (!new File(current_log_file).exists())
			{
				try
				{
					FileUtils.writeFile(current_log_file, "---log start---");
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			try
			{
				FileUtils.appendFile(current_log_file, "\n" + new_message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			System.out.println(new_message);
		}
	}

	public static String getCurrent_log_file()
	{
		return current_log_file;
	}
}