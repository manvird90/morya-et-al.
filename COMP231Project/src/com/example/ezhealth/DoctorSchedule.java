package com.example.ezhealth;

import java.io.Serializable;

public class DoctorSchedule implements Serializable {
	private int _scheduleId, _doctorId;
	private String _day, _dutyStart, _dutyEnd;
	
	public DoctorSchedule(){
		
	}
	
	public void setScheduleId(int scheduleId){
		this._scheduleId = scheduleId;
	}
	public int getScheduleId(){
		return this._scheduleId;
	}
	
	public void setDoctorId(int doctorId){
		this._doctorId = doctorId;
	}
	public int getDoctorId(){
		return this._doctorId;
	}
	
	public void setDay(String day){
		this._day = day;
	}
	public String getDay(){
		return this._day;
	}
	
	public void setDutyStartTime(String startTime){
		this._dutyStart = startTime;
	}
	public String getDutyStartTime(){
		return this._dutyStart;
	}
	
	public void setDutyEndTime(String endTime){
		this._dutyEnd = endTime;
	}
	public String getDutyEndTime(){
		return this._dutyEnd;
	}

}
