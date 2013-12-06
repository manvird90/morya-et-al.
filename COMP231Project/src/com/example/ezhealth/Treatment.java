package com.example.ezhealth;

public class Treatment {

	private int _appointmentId, _treatmentId;
	private String _symptoms, _prescription, _treatment;
	
	public Treatment(){
		
	}
	
	public void setAppointmentId(int appointmentId){
		this._appointmentId = appointmentId;
	}
	public int getAppointmentId(){
		return this._appointmentId;
	}
	
	public void setTreatmentId(int treatmentId){
		this._treatmentId = treatmentId;
	}
	public int getTreatmentId(){
		return this._treatmentId;
	}
	
	
	
	public void setPrescription(String prescription){
		this._prescription = prescription;
	}
	public String getPrescription(){
		return this._prescription;
	}
	
	public void setSymptoms(String symptoms){
		this._symptoms = symptoms;
	}
	public String getSymptoms(){
		return this._symptoms;
	}
	
	public void setTreatment(String treatment){
		this._treatment = treatment;
	}
	public String getTreatment(){
		return this._treatment;
	}
	
	
}
