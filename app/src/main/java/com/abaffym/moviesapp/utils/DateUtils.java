package com.abaffym.moviesapp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static String toSimpleString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy", Locale.getDefault());
		return dateFormat.format(date);
	}
}
