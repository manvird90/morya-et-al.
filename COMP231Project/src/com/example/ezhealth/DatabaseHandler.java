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
    
    //ADMINKEY TABLE variable
    private static final String TABLE_ADMINKEY = "AdminKey";
    private static final String COL_ADMINKEYID = "AdminKeyId";
    private static final String COL_ADMINKEY = "AdminKey";
    
    //USERLOGIN TABLE variable
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
    
  //Doctor Registration

    private static final String TABLE_ADD_DOC = "DocReg";
    private static final String COL_DOCTORID = "DoctorId";//PrimaryKey
    private static final String COL_DOCTORLOGINID = "DoctorLoginId";//Foreign Key
    private static final String COL_EXP = "Experience";
    private static final String COL_SPECIALITY = "Speciality";

    
    //ADMIN REGISTARION BY SACHIN PATEL
    
    //Admin Registration Table
    private static final String TABLE_ADMINREG = "AdminReg";
    private static final String COL_ADMINID = "AdminId";//PrimaryKey
    private static final String COL_ADMINLOGINID = "AdminLoginId";//Foreign Key
    private static final String COL_ADMINFIRSTNAME = "FirstName";
    private static final String COL_ADMINLASTNAME = "LastName";
    private static final String COL_ADMINDOB = "DateOfBirth";
    private static final String COL_ADMINEMAIL = "Email";
    private static final String COL_ADMINPHONE = "Phone";
    private static final String COL_ADMINGENDER = "Gender";
    private static final String COL_ADMINAPARTMENT = "Apartment";
    private static final String COL_ADMINSTREET = "Street";
    private static final String COL_ADMINCITY = "City";
    private static final String COL_ADMINPROVINCE = "Province";
    private static final String COL_ADMINCOUNTRY = "Country";
    private static final String COL_ADMINPOSTALCODE = "PostalCode";
    
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	try{
    		
    		//Admin key table by jignesh patel
    		
    		String CREATE_ADMINKEY_TABLE = "CREATE TABLE " + TABLE_ADMINKEY + "("
                    + COL_ADMINKEYID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
            		+ COL_ADMINKEY + " TEXT NOT NULL)";
            
            db.execSQL(CREATE_ADMINKEY_TABLE);
    		
    	//USERLOGIN TABLE for main page by jignesh patel	
    	String CREATE_USERLOGIN_TABLE = "CREATE TABLE " + TABLE_USERLOGIN + "("
                + COL_USERLOGINID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ COL_USERTYPE + " TEXT NOT NULL, "
                + COL_USERNAME + " TEXT NOT NULL UNIQUE, "
        		+ COL_USERPASSWORD + " TEXT NOT NULL)";
        
        db.execSQL(CREATE_USERLOGIN_TABLE);
        //Patient reg and vital info table by jignesh patel
        
        //TOTAL 16 COLUMNS
        String CREATE_PATIENTREG_TABLE = "CREATE TABLE " + TABLE_PATIENTREG + "("
                + COL_PATIENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_PATIENTLOGINID + " INTEGER NOT NULL UNIQUE, " // FOREIGN KEY
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
        
        //ADMIN TABLE by sachin patel
        
        String CREATE_ADMINREG_TABLE = "CREATE TABLE " + TABLE_ADMINREG + "("
                + COL_ADMINID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_ADMINLOGINID + " INTEGER NOT NULL UNIQUE, " // FOREIGN KEY
        		+ COL_ADMINFIRSTNAME + " TEXT NOT NULL, "
                + COL_ADMINLASTNAME + " TEXT NOT NULL, "
                + COL_ADMINGENDER + " TEXT NOT NULL, "
                + COL_ADMINDOB + " TEXT NOT NULL, "//make it not null
                + COL_ADMINEMAIL + " TEXT NOT NULL, "
                + COL_ADMINPHONE + " TEXT NOT NULL, "
                + COL_ADMINAPARTMENT + " TEXT NOT NULL, "
                + COL_ADMINSTREET + " TEXT NOT NULL, "
                + COL_ADMINCITY + " TEXT NOT NULL, "
                + COL_ADMINPROVINCE + " TEXT NOT NULL, "
                + COL_ADMINCOUNTRY + " TEXT NOT NULL, "
                + COL_ADMINPOSTALCODE + " TEXT NOT NULL, "
                + "FOREIGN KEY ("+COL_ADMINLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";
        
        db.execSQL(CREATE_ADMINREG_TABLE);
        
//      Doctor table by manvir  
        String CREATE_ADD_DOC_TABLE = "CREATE TABLE " + TABLE_ADD_DOC + "("
                + COL_DOCTORID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_DOCTORLOGINID + " INTEGER NOT NULL UNIQUE, "
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
                + COL_EXP + " TEXT NOT NULL, "
               + COL_SPECIALITY + " TEXT NOT NULL, "
               + "FOREIGN KEY ("+COL_DOCTORLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";

        db.execSQL(CREATE_ADD_DOC_TABLE);
        
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_DOC);
        // Create tables again
        onCreate(db);

    	}
    
    //Methods by jignesh patel
    
    public void addAdminKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
      //  values.put(COL_USERLOGINID, ul.getUserLoginId());
        values.put(COL_ADMINKEYID, key);
        db.insert(TABLE_ADMINKEY, null, values);
        db.close(); // Closing database connection
    }
    
    public boolean getAdminKey(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_ADMINKEY+" where "+COL_ADMINKEY+" = \""+key+"\"", null);
      boolean found;
        if (cursor!=null) { 
        	found =  true;
        } else {
        	found = false;
        }
        cursor.close();
        db.close();
        
        return found;
        
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

    //method to add admin by sachin patel - return admin class
    
    public void addAdmin(Admin ad) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_ADMINLOGINID, ad.getAdminLoginId()); 
        values.put(COL_FIRSTNAME, ad.getFirstName()); 
        values.put(COL_LASTNAME, ad.getLastName()); 
        values.put(COL_GENDER, ad.getGender()); 
        values.put(COL_DOB, ad.getDateOfBirth()); 
        values.put(COL_EMAIL, ad.getEmail());
        values.put(COL_PHONE, ad.getPhone()); 
        values.put(COL_APARTMENT, ad.getApartment());
        values.put(COL_STREET, ad.getStreet());
        values.put(COL_CITY, ad.getStreet());
        values.put(COL_PROVINCE, ad.getProvince());
        values.put(COL_COUNTRY, ad.getCountry());
        values.put(COL_POSTALCODE, ad.getPostalCode());
      
        // Inserting Row
        db.insert(TABLE_ADMINREG, null, values);
        db.close(); // Closing database connection
    }
    
    public int getAdminId(String firstName, String lastName, int adminLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_ADMINREG+" where "+COL_ADMINLOGINID+" = "+
        							adminLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }

    public void addDoctor(Doctor dt) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_DOCTORLOGINID, dt.getDoctorLoginId()); 
        values.put(COL_FIRSTNAME, dt.getFirstName()); 
        values.put(COL_LASTNAME, dt.getLastName()); 
        values.put(COL_GENDER, dt.getGender()); 
        values.put(COL_DOB, dt.getDateOfBirth()); 
        values.put(COL_EMAIL, dt.getEmail());
        values.put(COL_PHONE, dt.getPhone()); 
        values.put(COL_APARTMENT, dt.getApartment());
        values.put(COL_STREET, dt.getStreet());
        values.put(COL_CITY, dt.getStreet());
        values.put(COL_PROVINCE, dt.getProvince());
        values.put(COL_COUNTRY, dt.getCountry());
        values.put(COL_POSTALCODE, dt.getPostalCode());
        values.put(COL_EXP, dt.getExp());
        values.put(COL_SPECIALITY, dt.getSpeciality()); 
     
        // Inserting Row
        db.insert(TABLE_ADD_DOC, null, values);
        db.close(); // Closing database connection
    }
    
    public int getDoctorId(String firstName, String lastName, int DoctorLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_ADD_DOC+" where "+COL_DOCTORLOGINID+" = "+
        							DoctorLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }
    
    //method to get patient by jignesh patel - return patient class
    public Patient getPatient(int patientId, String firstName, String lastName){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENTREG+" where "+COL_PATIENTID+" = "+
        							patientId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
        Patient patient = new Patient();
        patient.setPatientId(cursor.getInt(0));
        patient.setPatientLoginId(cursor.getInt(1));
        patient.setFirstName(cursor.getString(2));
        patient.setLastName(cursor.getString(3));
        patient.setGender(cursor.getString(4));
        patient.setDateOfBirth(cursor.getString(5));
        patient.setEmail(cursor.getString(6));
        patient.setPhone(cursor.getString(7));
        patient.setApartment(cursor.getString(8));
        patient.setStreet(cursor.getString(9));
        patient.setCity(cursor.getString(10));
        patient.setProvince(cursor.getString(11));
        patient.setCountry(cursor.getString(12));
        patient.setPostalCode(cursor.getString(13));
        patient.setHealthPolicyNumber(cursor.getString(14));
        patient.setInsuranceCompany(cursor.getString(15));
    	cursor.close();
    	db.close();
    	return patient;
    }

}