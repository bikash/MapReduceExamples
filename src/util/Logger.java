package util;


public class Logger {
	
	public final static int DEBUG = 3;
	
	public final static int BEST_SOLUTION = 2;
	
	public final static int NONE = 1;
	
	protected static int level = NONE;
	
	public static void log(int level, String message) {
		if (level <= Logger.level) {
			System.out.println(message);
		}
	}
	
	public static void setLevel(int level){
		Logger.level = level;
	}

}
