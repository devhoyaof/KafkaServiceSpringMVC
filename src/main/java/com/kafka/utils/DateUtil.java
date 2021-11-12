package com.kafka.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
	/**
	 * unixTime을 format에 맞게 변환
	 * 
	 * @param dateTime
	 * @param format
	 * @return
	 */
	public static String getDateStr(long dateTime, String format)
	{
		String ret = null;

		try
		{
			Date d = new Date(dateTime);

			SimpleDateFormat sdf = new SimpleDateFormat(format);

			ret = sdf.format(d);
		}
		catch (Exception e)
		{
			ret = "";
		}

		return ret;
	}

	/**
	 * default format으로 현재 시간
	 * 
	 * @return
	 */
	public static String getDateStr()
	{
		String ret = null;

		try
		{
			Date d = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

			ret = sdf.format(d);
		}
		catch (Exception e)
		{
			ret = "";
		}

		return ret;
	}

	/**
	 * format으로 현재 시간
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateStr(String format)
	{
		String ret = null;

		try
		{
			Date d = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat(format);

			ret = sdf.format(d);
		}
		catch (Exception e)
		{
			ret = "";
		}

		return ret;
	}
	
	
	/**
	 * Date형 String을 Date로 변환
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	public static Date getDate(String dateString, String formatString)
	{
		Date date = null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			date = formatter.parse(dateString);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
		}
		return date;
	}

	/**
	 * date 형이 맞는지 check
	 * 
	 * @param s
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date check(String s, String format) throws ParseException
	{
		if (s == null)
			throw new ParseException("date string to check is null", 0);
		if (format == null)
			throw new ParseException("format string to check date is null", 0);

		SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
		Date date = null;

		try
		{
			date = formatter.parse(s);
		}
		catch (ParseException e)
		{
			throw new ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		if (!formatter.format(date).equals(s))
			throw new ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);

		return date;
	}

	/**
	 * 현재까지 개월 수
	 * 
	 * @param from
	 * @param to
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static int monthsBetween(String from, String to, String format) throws ParseException
	{
		/*
		 * Date fromDate = check(from, format); Date toDate = check(to, format);
		 * 
		 * // if two date are same, return 0. if (fromDate.compareTo(toDate) == 0) return 0;
		 */

		int ret = 0;

		if (from != null)
		{
			if (!from.equals(to))
			{
				int strtYear = Integer.parseInt(from.substring(0, 4));
				int strtMonth = Integer.parseInt(from.substring(4, 6));

				int endYear = Integer.parseInt(to.substring(0, 4));
				int endMonth = Integer.parseInt(to.substring(4, 6));

				ret = (endYear - strtYear) * 12 + (endMonth - strtMonth);
			}
		}
		else
		{
			ret = -1;
		}

		return ret;
	}
	
}
