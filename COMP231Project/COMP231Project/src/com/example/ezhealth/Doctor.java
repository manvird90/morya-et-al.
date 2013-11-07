package com.example.ezhealth;

import java.sql.Date;

public class Doctor {
	int _doctorId;
	int _userLoginId;
	String _dateOfBirth;
    String _firstName;
    String _lastName;
    String _email;
    String _phone;
    String _gender;
    String _apartment;
    String _street;
    String _city;
    String _province;
    String _country;
    String _postalCode;
    String _exp;
    String _speciality;
    
    public Doctor(){
    	
    }
    public Doctor(int uid, String firstName, String lastName, String gender, String dob, String email, String phone, String apt, String street,
    		String city, String province, String country, String postalCode, String exp, String spl){
    	
        this._userLoginId=uid;
        this._firstName=firstName;
        this._lastName=lastName;
        this._gender=gender;
        this._dateOfBirth = dob;
        this._apartment = apt;
        this._street = street;
        this._city = city;
        this._province = province;
        this._email = email;
        this._phone = phone;
        this._country = country;
        this._postalCode = postalCode;
        this._exp = exp;
        this._speciality = spl;
    }
    
    
    public Doctor(int did, int uid, String firstName, String lastName, String gender, String dob, String email, String phone, String apt, String street,
    		String city, String province, String country, String postalCode, String exp, String spl){
    	this._doctorId=did;
        this._userLoginId=uid;
        this._firstName=firstName;
        this._lastName=lastName;
        this._gender=gender;
        this._dateOfBirth = dob;
        this._apartment = apt;
        this._street = street;
        this._city = city;
        this._province = province;
        this._email = email;
        this._phone = phone;
        this._country = country;
        this._postalCode = postalCode;
        this._exp = exp;
        this._speciality = spl;
    }
    
    // getting ID
    public int getDoctorId(){
        return this._doctorId;
    }
    public void setDoctorId(int id){
        this._doctorId = id;
    }
    
    public int getDoctorLoginId(){
        return this._userLoginId;
    }
    public void setDoctorLoginId(int id){
        this._userLoginId = id;
    }
    
    public String getFirstName(){
        return this._firstName;
    }
    public void setFirstName(String fn){
        this._firstName = fn;
    }
    
    public String getLastName(){
        return this._lastName;
    }
    public void setLastName(String ln){
        this._lastName = ln;
    }
    
    public String getGender(){
        return this._gender;
    }
    public void setGender(String gnd){
        this._gender = gnd;
    }
    
    public String getDateOfBirth(){
        return this._dateOfBirth;
    }
    public void setDateOfBirth(String dob){
        this._dateOfBirth = dob;
    }
    
    public String getEmail(){
        return this._email;
    }
    public void setEmail(String email){
        this._email = email;
    }
    
    public String getPhone(){
        return this._phone;
    }
    public void setPhone(String email){
        this._phone = email;
    }
    
    public String getApartment(){
        return this._apartment;
    }
    public void setApartment(String app){
        this._apartment = app;
    }
    
    public String getStreet(){
        return this._street;
    }
    public void setStreet(String st){
        this._street = st;
    }
    
    public String getCity(){
        return this._city;
    }
    public void setCity(String city){
        this._city = city;
    }
    
    public String getProvince(){
        return this._province;
    }
    public void setProvince(String pv){
        this._province =pv;
    }
    
    public String getCountry(){
        return this._country;
    }
    
    public void setCountry(String ctry){
        this._country =ctry;
    }
    
    public String getPostalCode(){
        return this._postalCode;
    }
    public void setPostalCode(String pc){
        this._postalCode =pc;
    }
    
    public String getExp(){
        return this._exp;
    }
    public void setExp(String exp){
        this._exp =exp;
    }
    
    public String getSpeciality(){
        return this._speciality;
    }
    public void setSpeciality(String spl){
        this._speciality = spl;
    }
    
    
}




