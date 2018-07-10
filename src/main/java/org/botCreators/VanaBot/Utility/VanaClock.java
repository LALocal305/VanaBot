package org.botCreators.VanaBot.Utility;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class VanaClock {
	private String[] elemental_days = new String[]{"Firesday", "Earthsday", "Watersday", "Windsday", "Iceday", "Lightningday", "Lightsday", "Darksday"};
	private Date earth_start_date;
	private Date vana_epoch;
	private Date epoch = new GregorianCalendar(1970, Calendar.JANUARY, 1).getTime();
	private double epoch_to_start_delta;
	private long vanadiel_offset;
	private long vana_time = 1117359360;//(((898 * 360) + 30) * 24 * 60 * 60) / 25;
	
	public VanaClock(){
		GregorianCalendar v_e = new GregorianCalendar(2002, Calendar.JUNE, 23);
		earth_start_date = v_e.getTime();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(earth_start_date); 
		cal.set(Calendar.HOUR_OF_DAY, 15);
		vana_epoch = cal.getTime();
		
		cal.setTime(vana_epoch);
		long ve =  cal.getTimeInMillis() / 1000L;
		cal.setTime(epoch);
		long e = cal.getTimeInMillis() / 1000L - 3600;
		
		epoch_to_start_delta = ve - e;
		vanadiel_offset = (long)Math.floor(epoch_to_start_delta) - vana_time;
	}
	
	public String getTime(){
		Date today = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		
		cal.setTime(today);
		cal.add(Calendar.HOUR, 1);
		double ts = cal.getTimeInMillis() / 1000L;
		cal.setTime(epoch); 
		double es = cal.getTimeInMillis() / 1000L;
		
		double time =  (Math.floor((ts-es) + .5 ) - vanadiel_offset) * 25;
				
		double day = Math.floor(time / 86400);
        double year = Math.floor(day / 360);
        double month = Math.floor((day % 360) / 30) + 1;
        double dayOfMonth = Math.floor(day % 30) + 1;
        int eleDay = (int)day % 8;
        double hour = Math.floor(time / 3600) % 24;
        double minute = Math.floor(time / 60) % 60;
        double second = Math.floor(time) % 60;
        DecimalFormat df = new DecimalFormat("00");
        
        String returned = ":calendar_spiral: **" + elemental_days[eleDay] + "** " + (int)year + "-" + (int)month + "-" + (int)dayOfMonth + 
        					"\n:clock12: " + df.format(hour) + ":" + df.format(minute) + ":" + df.format(second);
        
        return returned;
	}
	
}

