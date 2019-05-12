package edu.ycp.cs320.entrelink.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateModifier {
	public DateModifier() {
		
	}
	
	public String getMonth(int m) {
		if		(m == 1)  return "January";
		else if	(m == 2)  return "February";
		else if	(m == 3)  return "March";
		else if (m == 4)  return "April";
		else if (m == 5)  return "May";
		else if (m == 6)  return "June";
		else if (m == 7)  return "July";
		else if (m == 9)  return "September";
		else if (m == 10) return "October";
		else if (m == 11) return "November";
		else			  return "December";
	}
	
	public String getCurrentDate() {
		DateFormat dateDay = new SimpleDateFormat("dd"); //yyyy/MM/dd HH:mm:ss
		DateFormat dateMonth = new SimpleDateFormat("MM");
		DateFormat restOfDate = new SimpleDateFormat("yyyy, hh:mm a");
		Date d = new Date();
		String month = getMonth(Integer.parseInt(dateMonth.format(d)));
		return dateDay.format(d) + " " + month + " " + restOfDate.format(d);
	}
}
