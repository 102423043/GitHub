package util;

public class LogInfo {
	
	public static void info(String format, Object... arguments) {
		System.out.printf(format, arguments);
		System.out.println();
	}
	
	public static void error(String format, Object... arguments) {
		System.err.printf(format, arguments);
		System.out.println();
	}
	
}
