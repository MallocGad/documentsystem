package com.ht.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getDate {
	private static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static String Date() {
		return df.format(new Date());
	}
	
}
