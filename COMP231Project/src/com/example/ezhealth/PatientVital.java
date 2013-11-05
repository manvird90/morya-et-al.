package com.example.ezhealth;

public class PatientVital {

	int _vitalInfoId;
	int _patientId;
    String _chronicDisease;
    String _diseaseHistory;
    String _allergy;
    float _height;
    float _weight;
    
    public PatientVital(){
    	
    }
    
    public PatientVital(int pi, float height, float weight, String allergy, String cd, String dh){
    	this._patientId = pi;
        this._height=height;
        this._weight=weight;
        this._chronicDisease = cd;
        this._diseaseHistory = dh;
        this._allergy = allergy;
    }
    public PatientVital(int vi, int pi, float height, float weight, String allergy, String cd, String dh){
    	this._vitalInfoId=vi;
    	this._patientId = pi;
        this._height=height;
        this._weight=weight;
        this._chronicDisease = cd;
        this._diseaseHistory = dh;
        this._allergy = allergy;
    }
    
    public int getPatientId(){
        return this._patientId;
    }
    public void setPatientId(int pi){
        this._patientId = pi;
    }
    
    public int getVitalInfoId(){
        return this._vitalInfoId;
    }
    public void setVItalInfoId(int vi){
        this._patientId = vi;
    }
    
    public float getHeight(){
        return this._height;
    }
    public void setHeight(float ht){
        this._height = ht;
    }
    public float getWeight(){
        return this._weight;
    }
    public void setWeight(float wt){
        this._weight = wt;
    }
    
    public String getAllergy(){
        return this._allergy;
    }
    public void setAllergy(String al){
        this._allergy = al;
    }
    
    public String getChronicDisease(){
        return this._chronicDisease;
    }
    public void setChronicDisease(String cd){
        this._chronicDisease = cd;
    }
    
    public String getDiseasHistory(){
        return this._diseaseHistory;
    }
    public void setDiseaseHistory(String dh){
        this._diseaseHistory = dh;
    }
}
