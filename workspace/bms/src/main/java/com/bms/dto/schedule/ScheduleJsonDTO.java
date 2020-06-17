package com.bms.dto.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ScheduleJsonDTO {
	
	private String schedule_code;
	private String schedule_sort_code;
    private String title;
    private String schedule_contents;
    private String start;
    private String end;
    private String schedule_start_time;
    private String schedule_end_time;
    private String schedule_istodolist;
    private boolean allDay;
    private String color;
    
    
    public ScheduleJsonDTO() {}
    
	public ScheduleJsonDTO(String title, String start, String end, Boolean allDay, String schedule_code, String color
							,String schedule_sort_code, String schedule_contents, String schedule_start_time, String schedule_end_time
							, String schedule_istodolist) {
		super();
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.schedule_code = schedule_code;
		this.color = color;
		this.schedule_sort_code = schedule_sort_code;
		this.schedule_contents = schedule_contents;
		this.schedule_start_time = schedule_start_time;
		this.schedule_end_time = schedule_end_time;
		this.schedule_istodolist = schedule_istodolist;
	}
	
	public String getSchedule_sort_code() {
		return schedule_sort_code;
	}

	public void setSchedule_sort_code(String schedule_sort_code) {
		this.schedule_sort_code = schedule_sort_code;
	}

	public String getSchedule_contents() {
		return schedule_contents;
	}

	public void setSchedule_contents(String schedule_contents) {
		this.schedule_contents = schedule_contents;
	}

	public String getSchedule_start_time() {
		return schedule_start_time;
	}

	public void setSchedule_start_time(String schedule_start_time) {
		this.schedule_start_time = schedule_start_time;
	}

	public String getSchedule_end_time() {
		return schedule_end_time;
	}

	public void setSchedule_end_time(String schedule_end_time) {
		this.schedule_end_time = schedule_end_time;
	}

	public String getSchedule_istodolist() {
		return schedule_istodolist;
	}

	public void setSchedule_istodolist(String schedule_istodolist) {
		this.schedule_istodolist = schedule_istodolist;
	}

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getSchedule_code() {
		return schedule_code;
	}
	
	public void setSchedule_code(String schedule_code) {
		this.schedule_code = schedule_code;
	}
	
	public boolean getAllDay() {
		return allDay;
	}
	
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEnd(String end) {
//		end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
		this.end = end;
	}

	@Override
	public String toString() {
		return "ScheduleJsonDTO [title=" + title + ", start=" + start + ", end=" + end + ", allDay=" + allDay
				+ ", schedule_code=" + schedule_code + ", color=" + color + ", schedule_sort_code=" + schedule_sort_code
				+ ", schedule_contents=" + schedule_contents + ", schedule_start_time=" + schedule_start_time
				+ ", schedule_end_time=" + schedule_end_time + ", schedule_istodolist=" + schedule_istodolist + "]";
	}



}