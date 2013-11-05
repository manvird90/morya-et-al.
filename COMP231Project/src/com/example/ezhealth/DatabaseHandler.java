package com.example.ezhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ezhealth.db";
    
    private static final String TABLE_USERLOGIN = "UserLogin";
    private static final String COL_USERLOGINID = "userLoginId";
    private static final String COL_USERNAME = "userName";
    private static final String COL_USERPASSWORD = "userPassword";
    private static final String COL_USERTYPE = "userType";
    
    //Patient Registration Table
    private static final String TABLE_PATIENTREG = "PatientReg";
    private static final String COL_PATIENTID = "PatientId";//PrimaryKey
    private static final String COL_PATIENTLOGINID = "PatientLoginId";//Foreign Key
    private static final String COL_FIRSTNAME = "FirstName";
    private static final String COL_LASTNAME = "LastName";
    private static final String COL_DOB = "DateOfBirth";
    private static final String COL_EMAIL = "Email";
    private static final String COL_PHONE = "Phone";
    private static final String COL_GENDER = "Gender";
    private static final String COL_APARTMENT = "Apartment";
    private static final String COL_STREET = "Street";
    private static final String COL_CITY = "City";
    private static final String COL_PROVINCE = "Province";
    private static final String COL_COUNTRY = "Country";
    private static final String COL_POSTALCODE = "PostalCode";
    private static final String COL_HEALTHPOLICYNUMBER = "HealthPolicyNumber";
    private static final String COL_INSURANCECOMPANY = "InsuranceCompany";
    
 
    //Patient Vital Information Table
    private static final String TABLE_VITALINFO = "VitalInfo";
    private static final String COL_VITALINFOID = "VitalInfoId";//PrimaryKey
    private static final String COL_VITALPATIENTID = "PatientId";//Foreign Key
    private static final String COL_HEIGHT = "Height";
    private static final String COL_WEIGHT = "Weight";
    private static final String COL_ALLERGY = "Allergy";
    private static final String COL_CHRONICDISEASE = "ChronicDisease";
    private static final String COL_DISEASEHISTORY = "DiseaseHistory";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	try{
    	String CREATE_USERLOGIN_TABLE = "CREATE TABLE " + TABLE_USERLOGIN + "("
                + COL_USERLOGINID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ COL_USERTYPE + " TEXT NOT NULL, "
                + COL_USERNAME + " TEXT NOT NULL UNIQUE, "
        		+ COL_USERPASSWORD + " TEXT NOT NULL)";
        
        db.execSQL(CREATE_USERLOGIN_TABLE);
        
        String CREATE_PATIENTREG_TABLE = "CREATE TABLE " + TABLE_PATIENTREG + "("
                + COL_PATIENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_PATIENTLOGINID + " INTEGER NOT NULL UNIQUE, "
        		+ COL_FIRSTNAME + " TEXT NOT NULL, "
                + COL_LASTNAME + " TEXT NOT NULL, "
                + COL_GENDER + " TEXT NOT NULL, "
                + COL_DOB + " TEXT NOT NULL, "//make it not null
                + COL_EMAIL + " TEXT NOT NULL, "
                + COL_PHONE + " TEXT NOT NULL, "
                + COL_APARTMENT + " TEXT NOT NULL, "
                + COL_STREET + " TEXT NOT NULL, "
                + COL_CITY + " TEXT NOT NULL, "
                + COL_PROVINCE + " TEXT NOT NULL, "
                + COL_COUNTRY + " TEXT NOT NULL, "
                + COL_POSTALCODE + " TEXT NOT NULL, "
                + COL_HEALTHPOLICYNUMBER + " TEXT NOT NULL, "
               + COL_INSURANCECOMPANY + " TEXT NOT NULL, "
               + "FOREIGN KEY ("+COL_PATIENTLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";
        
        db.execSQL(CREATE_PATIENTREG_TABLE);
        
        String CREATE_VITALINFO_TABLE = "CREATE TABLE " + TABLE_VITALINFO + "("
                + COL_VITALINFOID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_VITALPATIENTID + " INTEGER NOT NULL UNIQUE, "
        		+ COL_HEIGHT + " REAL NOT NULL, "
                + COL_WEIGHT + " REAL NOT NULL, "
                + COL_ALLERGY + " TEXT NOT NULL, "
                + COL_CHRONICDISEASE + " TEXT NOT NULL, "
                + COL_DISEASEHISTORY + " TEXT NOT NULL, "
                + "FOREIGN KEY ("+COL_VITALPATIENTID+") REFERENCES "+TABLE_PATIENTREG+"("+COL_PATIENTID+"))";
        
        db.execSQL(CREATE_VITALINFO_TABLE);
        
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }
    
    
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERLOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTREG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALINFO);
 
        // Create tables again
        onCreate(db);

    	}
    
    public void addUser(UserLogin ul) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
      //  values.put(COL_USERLOGINID, ul.getUserLoginId());
        values.put(COL_USERTYPE, ul.getUserType());
        values.put(COL_USERNAME, ul.getUserName()); // Contact Name
        values.put(COL_USERPASSWORD, ul.getUserPassword()); // Contact Phone Number
     
        // Inserting Row
        db.insert(TABLE_USERLOGIN, null, values);
        db.close(); // Closing database connection
    }
    
    public boolean getUserLogin(String ut, String un, String up) {
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery("Select * from "+TABLE_USERLOGIN, null);
	     // if (cursor != null)
	    	 
	         while(cursor.moveToNext()){
	    	
	        	 if (cursor.getString(1).equals(ut)){
	        		 if (cursor.getString(2).equals(un)){
	        			 if(cursor.getString(3).equals(up)){
	        				 return true;
	    				 }
	        		 }
	        	 }
	         } 
	      
	         cursor.close();
	         db.close();
	        return false;
    }
    
    public int getUserId(String ut, String un, String up){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_USERLOGIN+" where "+COL_USERNAME+" = \""+un+"\" and "+COL_USERTYPE+" = \""+ut+"\" and "+COL_USERPASSWORD+" = \""+up+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    }
    
   
    
    public void addPatient(Patient pt) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_PATIENTLOGINID, pt.getPatientLoginId()); 
        values.put(COL_FIRSTNAME, pt.getFirstName()); 
        values.put(COL_LASTNAME, pt.getLastName()); 
        values.put(COL_GENDER, pt.getGender()); 
        values.put(COL_DOB, pt.getDateOfBirth()); 
        values.put(COL_EMAIL, pt.getEmail());
        values.put(COL_PHONE, pt.getPhone()); 
        values.put(COL_APARTMENT, pt.getApartment());
        values.put(COL_STREET, pt.getStreet());
        values.put(COL_CITY, pt.getStreet());
        values.put(COL_PROVINCE, pt.getProvince());
        values.put(COL_COUNTRY, pt.getCountry());
        values.put(COL_POSTALCODE, pt.getPostalCode());
        values.put(COL_HEALTHPOLICYNUMBER, pt.getHealthPolicyNumber());
        values.put(COL_INSURANCECOMPANY, pt.getInsuranceCompany()); 
     
        // Inserting Row
        db.insert(TABLE_PATIENTREG, null, values);
        db.close(); // Closing database connection
    }
    
    public int getPatientId(String firstName, String lastName, int patientLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENTREG+" where "+COL_PATIENTLOGINID+" = "+
        							patientLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }
    
    public void addVitalInfo(PatientVital pv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(COL_VITALPATIENTID, pv.getPatientId()); 
        values.put(COL_HEIGHT, pv.getHeight()); 
        values.put(COL_WEIGHT, pv.getWeight()); 
        values.put(COL_ALLERGY, pv.getAllergy()); 
        values.put(COL_CHRONICDISEASE, pv.getChronicDisease()); 
        values.put(COL_DISEASEHISTORY, pv.getDiseasHistory());
     
        // Inserting Row
        db.insert(TABLE_VITALINFO, null, values);
        db.close(); // Closing database connection
    }
    
    public Cursor getPatientInformation(int patientId, String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENTREG+" where "+COL_PATIENTID+" = "+
				patientId+" and "+COL_FIRSTNAME+" = \""+
				firstName+"\" and "+COL_LASTNAME+" = \""+
				lastName+"\"", null);
        cursor.moveToFirst();
        
        return cursor;
       
    }
}
