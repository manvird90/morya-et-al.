package com.example.ezhealth;

public class UserLogin {
	 //private variables
	int _userLoginId;
    String _userType;
    String _userName;
    String _userPassword;
     
    // Empty constructor
 
    
    public UserLogin(String ut, String un, String up){
    	this._userType = ut;
        this._userName = un;
        this._userPassword = up;
         
    }
    
    public UserLogin(int id, String ut, String un, String up){
    	this._userLoginId = id;
    	this._userType = ut;
        this._userName = un;
        this._userPassword = up;
         
    }
    
    // getting ID
    public int getUserLoginId(){
        return this._userLoginId;
    }
    public String getUserType(){
        return this._userType;
    }
    public String getUserName(){
        return this._userName;
    }
    public String getUserPassword(){
        return this._userPassword;
    }
    // setting id
    public void setUserLoginId(int id){
      this._userLoginId = id;
    }
    public void setUserType(String ut){
        this._userType = ut;
    }
    
    public void setUserName(String un){
        this._userName = un;
    }
    public void setUserPassword(String up){
        this._userPassword = up;
    }
     
}
