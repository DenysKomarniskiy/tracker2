package utils;

public class Rights {

	public static int TS_VIEW = 1 << 0;
	public static int TS_EDIT = 1 << 1;
	public static int ST_VIEW = 1 << 2;
	public static int ST_EDIT = 1 << 3;
	public static int ALL = TS_VIEW | TS_EDIT | ST_VIEW | ST_EDIT;
	public static int DEFAULT = TS_VIEW | ST_VIEW;

}
