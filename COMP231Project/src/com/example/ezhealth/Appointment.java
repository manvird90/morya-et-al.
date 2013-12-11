package com.example.ezhealth;

import java.io.Serializable;

public class Appointment implements Serializable{
	private int _appointmentId, _doctorId, _patientId, _deptId;
	private String _date, _time;
	
	public Appointment(){
		
	}
	
	public void setAppointmentId(int appointmentId){
		this._appointmentId = appointmentId;
	}
	public int getAppointmentId(){
		return this._appointmentId;
	}
	
	public void setDoctorId(int doctorId){
		this._doctorId = doctorId;
	}
	public int getDoctorId(){
		return this._doctorId;
	}
	
	public void setPatientId(int patientId){
		this._patientId = patientId;
	}
	public int getPatientId(){
		return this._patientId;
	}
	public void setDepartmentId(int departmentId){
		this._deptId = departmentId;
	}
	public int getDepartmentId(){
		return this._deptId;
	}
	public void setDate(String date){
		this._date = date;
	}
	public String getDate(){
		return this._date;
	}
	
	public void setTime(String time){
		this._time = time;
	}
	public String getTime(){
		return this._time;
	}
	
	

}
