package com.example.ezhealth;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Element;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{
	
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ezhealth.db";
    
    //ADMINKEY TABLE variable
    private static final String TABLE_ADMINKEY = "SoftwareKey";
    private static final String COL_ADMINKEYID = "AdminKeyId";
    private static final String COL_ADMINKEY = "AdminKey";
    
    //USERLOGIN TABLE variable by Jignesh Patel
    private static final String TABLE_USERLOGIN = "UserLogin";
    private static final String COL_USERLOGINID = "userLoginId";
    private static final String COL_USERNAME = "userName";
    private static final String COL_USERPASSWORD = "userPassword";
    private static final String COL_USERTYPE = "userType";
    
    //Patient Registration Table by Jignesh Patel
    private static final String TABLE_PATIENT = "Patient";
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
    
 
    //Patient Vital Information Table by Jignesh Patel
    private static final String TABLE_VITALINFO = "VitalInfo";
    private static final String COL_VITALINFOID = "VitalInfoId";//PrimaryKey
    private static final String COL_VITALPATIENTID = "PatientId";//Foreign Key
    private static final String COL_HEIGHT = "Height";
    private static final String COL_WEIGHT = "Weight";
    private static final String COL_ALLERGY = "Allergy";
    private static final String COL_CHRONICDISEASE = "ChronicDisease";
    private static final String COL_DISEASEHISTORY = "DiseaseHistory";
    
    //Doctor Schedule by Jignesh Patel
    
    private static final String TABLE_DOCTORSCHEDULE = "DoctorSchedule";
    private static final String COL_SCHEDULEID = "ScheduleId";//PrimaryKey
    private static final String COL_SCH_DOCTORID = "DoctorId";//Foreign Key
    private static final String COL_DAY = "Day";
    private static final String COL_DUTYSTART = "DutyStart";
    private static final String COL_DUTYEND = "DutyEnd";
    
    //Department Table by Jignesh Patel
    private static final String TABLE_DEPARTMENT = "Department";
    private static final String COL_DEPARTMENTID = "DepartmentId";
    private static final String COL_DEPARTMENTNAME = "DepartmentName";
    
    //Appointment Table by Jignesh Patel
    private static final String TABLE_APPOINTMENT = "Appointment";
    private static final String COL_APPOINTMENTID = "AppointmentId";
    private static final String COL_APP_PATIENTID = "PatientId";
    private static final String COL_APP_DOCTORID = "DoctorId";
    private static final String COL_APP_DEPARTMENTID = "DepartmentId";
    private static final String COL_DATE = "DateOfAppointment";
    private static final String COL_TIME = "TimeOfAppointment";
    
    //Doctor Availability Table
    private static final String TABLE_AVAILABILITY = "Availability";
    private static final String COL_AVAILABILITYID = "AvailabilityId";
    private static final String COL_AVA_APPOINTMENTID = "AppointmentId";
    private static final String COL_AVA_DATE = "Date";
    private static final String COL_AVA_TIME = "Time";
    private static final String COL_ISAVAILABLE = "IsAvailable";
    
    
  //Doctor Registration by Manvir Kaur

    private static final String TABLE_DOCTOR = "Doctor";
    private static final String COL_DOCTORID = "DoctorId";//PrimaryKey
    private static final String COL_DOCTORLOGINID = "DoctorLoginId";//Foreign Key
    private static final String COL_EXP = "Experience";
    private static final String COL_SPECIALITY = "Speciality";
    private static final String COL_DOC_DEPARTMENTID = "DepartmentId";

    //Receptionist Registration by Manvir Kaur
    private static final String TABLE_RECEPTIONISTREGISTRATION = "Receptionist";
    private static final String COL_RECEPTIONISTID = "ReceptionistId";//PrimaryKey
    private static final String COL_RECEPTIONISTLOGINID = "ReceptionistLoginId";//Foreign Key
    
    //ADMIN REGISTARION BY SACHIN PATEL
    
    //Admin Registration Table
    private static final String TABLE_ADMIN = "Administrator";
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
    
    //Lab registration by Manvir
    
    private static final String TABLE_LABREGISTRATION = "LabStaff";
    private static final String COL_LABID = "LabId";//PrimaryKey
    private static final String COL_LABLOGINID = "LabLoginId";//Foreign Key
    
    
    //Treatment table by Jignesh Patel
 
    private static final String TABLE_TREATMENT = "Treatment";
    private static final String COL_TREATMENTID = "TreatmentId";
    private static final String COL_TRE_APPOINTMENTID = "AppointmentId";
    private static final String COL_SYMPTOMS = "Symptoms";
    private static final String COL_TREATMENT = "TREATMENT";
    private static final String COL_PRESCRIPTION = "PRESCRIPTION";
    
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
        
        //Department by JigneshPatel
        String CREATE_DEPARTMENT_TABLE = "CREATE TABLE " + TABLE_DEPARTMENT + "("
                + COL_DEPARTMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
        		+ COL_DEPARTMENTNAME + " TEXT NOT NULL)";
        
        db.execSQL(CREATE_DEPARTMENT_TABLE);
        
        //Patient registration and vital info table by jignesh patel
        
        //TOTAL 16 COLUMNS
        String CREATE_PATIENTREG_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
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
                + "FOREIGN KEY ("+COL_VITALPATIENTID+") REFERENCES "+TABLE_PATIENT+"("+COL_PATIENTID+"))";
        
        db.execSQL(CREATE_VITALINFO_TABLE);
        
        //ADMIN TABLE by sachin patel
        
        String CREATE_ADMINREG_TABLE = "CREATE TABLE " + TABLE_ADMIN + "("
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
        
//      Doctor table by manvir  kaur - 17 columns -  modified by jignesh patel department 
        String CREATE_DOCTOR_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
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
               + COL_DOC_DEPARTMENTID + " INTEGER NOT NULL, "
                + "FOREIGN KEY ("+COL_DOC_DEPARTMENTID+") REFERENCES "+TABLE_DEPARTMENT+"("+COL_DEPARTMENTID+"), "
               + "FOREIGN KEY ("+COL_DOCTORLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";

        db.execSQL(CREATE_DOCTOR_REGISTRATION_TABLE);
        

        //add doctor schedule by jignesh patel
        String CREATE_DOCTOR_SCHEDULE_TABLE = "CREATE TABLE " + TABLE_DOCTORSCHEDULE + "("
                + COL_SCHEDULEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_SCH_DOCTORID + " INTEGER NOT NULL, "
        		+ COL_DAY + " TEXT NOT NULL, "
                + COL_DUTYSTART + " TEXT NOT NULL, "
                + COL_DUTYEND + " TEXT NOT NULL, "
               + "FOREIGN KEY ("+COL_SCH_DOCTORID+") REFERENCES "+TABLE_DOCTOR+"("+COL_DOCTORID+"))";

        db.execSQL(CREATE_DOCTOR_SCHEDULE_TABLE);
        
        
        //Appointment table by jignesh patel
        
        String CREATE_APPOINTMENT_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT + "("
                + COL_APPOINTMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_APP_PATIENTID + " INTEGER NOT NULL, "
                + COL_APP_DOCTORID + " INTEGER NOT NULL, "
                + COL_APP_DEPARTMENTID + " INTEGER NOT NULL, "
        		+ COL_DATE + " TEXT NOT NULL, "
                + COL_TIME + " TEXT , "
                + "FOREIGN KEY ("+COL_APP_PATIENTID+") REFERENCES "+TABLE_PATIENT+"("+COL_PATIENTID+"), "
                + "FOREIGN KEY ("+COL_APP_DOCTORID+") REFERENCES "+TABLE_DOCTOR+"("+COL_DOCTORID+"), "
               + "FOREIGN KEY ("+COL_APP_DEPARTMENTID+") REFERENCES "+TABLE_DEPARTMENT+"("+COL_DEPARTMENTID+"))";
        
        db.execSQL(CREATE_APPOINTMENT_TABLE);
        
        //treatment table by jignesh patel
        String CREATE_TREATMENT_TABLE = "CREATE TABLE " + TABLE_TREATMENT + "("
                + COL_TREATMENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_TRE_APPOINTMENTID + " INTEGER NOT NULL UNIQUE, "
        		+ COL_SYMPTOMS + " TEXT NOT NULL, "
                + COL_PRESCRIPTION + " TEXT NOT NULL, "
                + COL_TREATMENT + " TEXT NOT NULL, "
               + "FOREIGN KEY ("+COL_TRE_APPOINTMENTID+") REFERENCES "+TABLE_APPOINTMENT+"("+COL_APPOINTMENTID+"))";
        db.execSQL(CREATE_TREATMENT_TABLE);
       
        //REceptionist table -- manvir kaur
        
        String CREATE_RECEPTIONIST_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_RECEPTIONISTREGISTRATION + "("
                + COL_RECEPTIONISTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_RECEPTIONISTLOGINID + " INTEGER NOT NULL UNIQUE, "
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
                + "FOREIGN KEY ("+COL_RECEPTIONISTLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";

        db.execSQL(CREATE_RECEPTIONIST_REGISTRATION_TABLE);
        
 //Lab table -- manvir kaur
        
        String CREATE_LAB_REGISTRATION_TABLE = "CREATE TABLE " + TABLE_LABREGISTRATION + "("
                + COL_LABID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_LABLOGINID + " INTEGER NOT NULL UNIQUE, "
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
                + "FOREIGN KEY ("+COL_RECEPTIONISTLOGINID+") REFERENCES "+TABLE_USERLOGIN+"("+COL_USERLOGINID+"))";

        db.execSQL(CREATE_LAB_REGISTRATION_TABLE);

        
        /*
        String CREATE_AVAILABILITY_TABLE = "CREATE TABLE " + TABLE_AVAILABILITY + "("
                + COL_AVAILABILITYID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_AVA_APPOINTMENTID + " INTEGER NOT NULL, "
        		+ COL_AVA_DATE + " TEXT NOT NULL, "
                + COL_AVA_TIME + " TEXT , "
                + COL_ISAVAILABLE + " TEXT, "
               + "FOREIGN KEY ("+COL_AVA_APPOINTMENTID+") REFERENCES "+TABLE_APPOINTMENT+"("+COL_APPOINTMENTID+"))";
        
        db.execSQL(CREATE_AVAILABILITY_TABLE);
        */
   
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VITALINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORSCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVAILABILITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINKEY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABREGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEPTIONISTREGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TREATMENT);
        // Create tables again
        onCreate(db);

    	}
    
    //Methods by jignesh patel
    
    public void addAdminKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_ADMINKEYID, key);
        db.insert(TABLE_ADMINKEY, null, values);
        db.close(); // Closing database connection
    }
    
    //methods add department by jignesh patel
    public void addDepartment(String departmentName) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_DEPARTMENTNAME, departmentName);
        db.insert(TABLE_DEPARTMENT, null, values);
        db.close();
    }
    
    public String getDepartmentName(int departmentId){
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery("Select * from "+TABLE_DEPARTMENT+" where "+COL_DEPARTMENTID+" = "+departmentId, null);
    	cursor.moveToFirst();
    	String deptName = cursor.getString(1);
    	cursor.close();
    	db.close();
    	return deptName;
    	
    }
    
    //methods get department arraylist by jignesh patel
    public ArrayList<String> getDepartmentList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DEPARTMENT, null);
    
        ArrayList<String> departments = new ArrayList<String>();
        while(cursor.moveToNext()){
        	departments.add(cursor.getString(1));
        }
        
     return departments;
        
    }
    
    //methods get admin key by jignesh patel
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
    
    //methods to add doctor schedule by jignesh patel
    public void addDoctorSchedule(DoctorSchedule ds) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_SCH_DOCTORID, ds.getDoctorId());
        values.put(COL_DAY, ds.getDay());
        values.put(COL_DUTYSTART, ds.getDutyStartTime());
        values.put(COL_DUTYEND, ds.getDutyEndTime());
        try{
        db.insert(TABLE_DOCTORSCHEDULE, null, values);
        }catch(SQLException e){
        }
        db.close();
    }
    //userlogin table jignesh patel
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
    //check unique username for the user by jignesh patel
    
    public boolean isUserNameAvailable(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_USERLOGIN+" where "+COL_USERNAME+" = \""+userName+"\"", null);
       
	    	 if (cursor.moveToFirst()){
	        		cursor.close();
	                 db.close();
	        		return false;
	        	 } 	 else {
	        		 cursor.close();
	                 db.close();
	        		 return true;
	        	 }
	  
        
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
    
    
    public int addPatient(Patient pt) {
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
        db.insert(TABLE_PATIENT, null, values);
        db.close(); // Closing database connection
        
        int pid;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("Select * from "+TABLE_PATIENT, null);
        cursor.moveToLast();
        pid = cursor.getInt(0);
        cursor.close();
        dbr.close();
        return pid;
    }
    
    public int getPatientId(String firstName, String lastName, int patientLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENT+" where "+COL_PATIENTLOGINID+" = "+
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
        db.insert(TABLE_ADMIN, null, values);
        db.close(); // Closing database connection
    }
    
    public int getAdminId(String firstName, String lastName, int adminLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_ADMIN+" where "+COL_ADMINLOGINID+" = "+
        							adminLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }

    //Add doctor by manvir dhalival -- modified dept id by jignesh patel
    public int addDoctor(Doctor dt) {
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
        values.put(COL_DOC_DEPARTMENTID, dt.getDepartmentId());
     
        // Inserting Row
        db.insert(TABLE_DOCTOR, null, values);
        db.close(); // Closing database connection
        
        int did;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("Select * from "+TABLE_DOCTOR, null);
        cursor.moveToLast();
        did = cursor.getInt(0);
        cursor.close();
        dbr.close();
        return did;
    }
    
    public int getDoctorId(String firstName, String lastName, int DoctorLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOCTORLOGINID+" = "+
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
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENT+" where "+COL_PATIENTID+" = "+
        							patientId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        Patient patient = null;
        if(cursor.moveToFirst()) {
        patient = new Patient();
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
        } else {
        	cursor.close();
          	db.close();
        	return patient;
        }
    	
    	
    }
    
    //get all patients in arraylist by jigneshkumar patel
    
    public ArrayList<Patient> getAllPatients(){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENT, null);
        ArrayList<Patient> listPatients = new ArrayList<Patient>();
        while(cursor.moveToNext()){
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
        listPatients.add(patient);
        }
        cursor.close();
    	db.close();
    	return listPatients;
    }
    
    public Doctor getDoctor(int doctorId, String firstName, String lastName){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOCTORID+" = "+
        							doctorId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        Doctor doctor = null;
        if(cursor.moveToFirst()){
        doctor = new Doctor();
        doctor.setDoctorId(cursor.getInt(0));
        doctor.setDoctorLoginId(cursor.getInt(1));
        doctor.setFirstName(cursor.getString(2));
        doctor.setLastName(cursor.getString(3));
        doctor.setGender(cursor.getString(4));
        doctor.setDateOfBirth(cursor.getString(5));
        doctor.setEmail(cursor.getString(6));
        doctor.setPhone(cursor.getString(7));
        doctor.setApartment(cursor.getString(8));
        doctor.setStreet(cursor.getString(9));
        doctor.setCity(cursor.getString(10));
        doctor.setProvince(cursor.getString(11));
        doctor.setCountry(cursor.getString(12));
        doctor.setPostalCode(cursor.getString(13));
        doctor.setExp(cursor.getString(14));
        doctor.setSpeciality(cursor.getString(15));
        doctor.setDepartmentId(cursor.getInt(16));
    	cursor.close();
    	db.close();
    	return doctor;
        } else {
        	cursor.close();
        	db.close();
        	return doctor;
        }
        
        
    }
    
    public Doctor getDoctor(int doctorId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOCTORID+" = "+doctorId, null);
        Doctor doctor = null;
        if(cursor.moveToFirst()){
        doctor = new Doctor();
        doctor.setDoctorId(cursor.getInt(0));
        doctor.setDoctorLoginId(cursor.getInt(1));
        doctor.setFirstName(cursor.getString(2));
        doctor.setLastName(cursor.getString(3));
        doctor.setGender(cursor.getString(4));
        doctor.setDateOfBirth(cursor.getString(5));
        doctor.setEmail(cursor.getString(6));
        doctor.setPhone(cursor.getString(7));
        doctor.setApartment(cursor.getString(8));
        doctor.setStreet(cursor.getString(9));
        doctor.setCity(cursor.getString(10));
        doctor.setProvince(cursor.getString(11));
        doctor.setCountry(cursor.getString(12));
        doctor.setPostalCode(cursor.getString(13));
        doctor.setExp(cursor.getString(14));
        doctor.setSpeciality(cursor.getString(15));
        doctor.setDepartmentId(cursor.getInt(16));
    	cursor.close();
    	db.close();
    	return doctor;
        } else {
        	cursor.close();
        	db.close();
        	return doctor;
        }
        
        
    }
    
    //Add Receptionist and get receptionist id Manvir
    public int addReceptionist(Receptionistdb rt) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_RECEPTIONISTLOGINID, rt.getReceptionistLoginId()); 
        values.put(COL_FIRSTNAME, rt.getFirstName()); 
        values.put(COL_LASTNAME, rt.getLastName()); 
        values.put(COL_GENDER, rt.getGender()); 
        values.put(COL_DOB, rt.getDateOfBirth()); 
        values.put(COL_EMAIL, rt.getEmail());
        values.put(COL_PHONE, rt.getPhone()); 
        values.put(COL_APARTMENT, rt.getApartment());
        values.put(COL_STREET, rt.getStreet());
        values.put(COL_CITY, rt.getStreet());
        values.put(COL_PROVINCE, rt.getProvince());
        values.put(COL_COUNTRY, rt.getCountry());
        values.put(COL_POSTALCODE, rt.getPostalCode());
     
        // Inserting Row
        db.insert(TABLE_RECEPTIONISTREGISTRATION, null, values);
        db.close(); // Closing database connection
        
        int rid;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("Select * from "+TABLE_RECEPTIONISTREGISTRATION, null);
        cursor.moveToLast();
        rid = cursor.getInt(0);
        cursor.close();
        dbr.close();
        return rid;
    }
    
    public int getReceptionistId(String firstName, String lastName, int ReceptionistLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_RECEPTIONISTREGISTRATION+" where "+COL_RECEPTIONISTLOGINID+" = "+
        							ReceptionistLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }
    
//get all doctors in arraylist by sachin patel
    
    public ArrayList<Doctor> getAllDoctors(){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR, null);
        ArrayList<Doctor> listDoctors = new ArrayList<Doctor>();
        while(cursor.moveToNext()){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(cursor.getInt(0));
        doctor.setDoctorLoginId(cursor.getInt(1));
        doctor.setFirstName(cursor.getString(2));
        doctor.setLastName(cursor.getString(3));
        doctor.setGender(cursor.getString(4));
        doctor.setDateOfBirth(cursor.getString(5));
        doctor.setEmail(cursor.getString(6));
        doctor.setPhone(cursor.getString(7));
        doctor.setApartment(cursor.getString(8));
        doctor.setStreet(cursor.getString(9));
        doctor.setCity(cursor.getString(10));
        doctor.setProvince(cursor.getString(11));
        doctor.setCountry(cursor.getString(12));
        doctor.setPostalCode(cursor.getString(13));
        doctor.setExp(cursor.getString(14));
        doctor.setSpeciality(cursor.getString(15));
       	doctor.setDepartmentId(cursor.getInt(16));
        listDoctors.add(doctor);
        }
        cursor.close();
    	db.close();
    	return listDoctors;
    }
    
    //sachin patel getpatient login Id//

    public int getPatientLoginId(String ut, String un, String up) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_USERLOGIN+" where "+COL_USERTYPE+" = \""+
        							ut+"\" and "+COL_USERNAME+" = \""+
        							un+"\" and "+COL_USERPASSWORD+" = \""+
        							up+"\"", null);
    
        if(cursor.moveToFirst()){
    	int i = cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return i;
         } else {
        	 int i =0;
        	 cursor.close();
        	 db.close();
        	 return i;
         }
      
    }
    
    public Patient getPatientByLoginId(int patientLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENT+" where "+COL_PATIENTLOGINID+" = "+
        							patientLoginId, null);
        
        Patient patient = null;
        if(cursor.moveToFirst()){
        patient = new Patient();
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
        }
    	return patient;
    	
    }
    
    //AddLab by Manvir
    
    public int addLab(Lab lb) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(COL_LABLOGINID, lb.getLabLoginId()); 
        values.put(COL_FIRSTNAME, lb.getFirstName()); 
        values.put(COL_LASTNAME, lb.getLastName()); 
        values.put(COL_GENDER, lb.getGender()); 
        values.put(COL_DOB, lb.getDateOfBirth()); 
        values.put(COL_EMAIL, lb.getEmail());
        values.put(COL_PHONE, lb.getPhone()); 
        values.put(COL_APARTMENT, lb.getApartment());
        values.put(COL_STREET, lb.getStreet());
        values.put(COL_CITY, lb.getStreet());
        values.put(COL_PROVINCE, lb.getProvince());
        values.put(COL_COUNTRY, lb.getCountry());
        values.put(COL_POSTALCODE, lb.getPostalCode());
     
        // Inserting Row
        db.insert(TABLE_LABREGISTRATION, null, values);
        db.close(); // Closing database connection
        
        int lid;
        SQLiteDatabase dbr = this.getReadableDatabase();
        Cursor cursor = dbr.rawQuery("Select * from "+TABLE_LABREGISTRATION, null);
        cursor.moveToLast();
        lid = cursor.getInt(0);
        cursor.close();
        dbr.close();
        return lid;
    }

    public int getLabId(String firstName, String lastName, int LabLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_LABREGISTRATION+" where "+COL_LABLOGINID+" = "+
        							LabLoginId+" and "+COL_FIRSTNAME+" = \""+
        							firstName+"\" and "+COL_LASTNAME+" = \""+
        							lastName+"\"", null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }
    
    //Jignesh patel
    //methods getDoctorNameList and doctorIdList
    public ArrayList<String> getDoctorNameList(int departmentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOC_DEPARTMENTID+" = "+departmentId, null);
    
        ArrayList<String> doctorNameList = new ArrayList<String>();
        while(cursor.moveToNext()){
        	doctorNameList.add(cursor.getString(2)+"\t"+cursor.getString(3));
        }
        
     return doctorNameList;
        
    }
    
    public ArrayList<Integer> getDoctorIdList(int departmentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOC_DEPARTMENTID+" = "+departmentId, null);
    
        ArrayList<Integer> doctorIdList = new ArrayList<Integer>();
        while(cursor.moveToNext()){
        	doctorIdList.add(cursor.getInt(16));
        }
        
     return doctorIdList;
        
    }

    //Sukhvir gill
    public int getDoctorLoginId(String ut, String un, String up) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_USERLOGIN+" where "+COL_USERTYPE+" = \""+
        							ut+"\" and "+COL_USERNAME+" = \""+
        							un+"\" and "+COL_USERPASSWORD+" = \""+
        							up+"\"", null);
    
        if(cursor.moveToFirst()){
    	int i = cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return i;
         } else {
        	 int i =0;
        	 cursor.close();
        	 db.close();
        	 return i;
         }
      
    }
    

    public Doctor getDoctorByLoginId(int doctorLoginId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_DOCTOR+" where "+COL_DOCTORLOGINID+" = "+
        							doctorLoginId, null);
        cursor.moveToFirst();
        Doctor doctor = new Doctor();
        doctor.setDoctorId(cursor.getInt(0));
        doctor.setDoctorLoginId(cursor.getInt(1));
        doctor.setFirstName(cursor.getString(2));
        doctor.setLastName(cursor.getString(3));
        doctor.setGender(cursor.getString(4));
        doctor.setDateOfBirth(cursor.getString(5));
        doctor.setEmail(cursor.getString(6));
        doctor.setPhone(cursor.getString(7));
        doctor.setApartment(cursor.getString(8));
        doctor.setStreet(cursor.getString(9));
        doctor.setCity(cursor.getString(10));
        doctor.setProvince(cursor.getString(11));
        doctor.setCountry(cursor.getString(12));
        doctor.setPostalCode(cursor.getString(13));
        doctor.setExp(cursor.getString(14));
        doctor.setSpeciality(cursor.getString(15));
       	doctor.setDepartmentId(cursor.getInt(16));
    	cursor.close();
    	db.close();
    	return doctor;
    }
    
    public Patient getPatient(int patientId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_PATIENT+" where "+COL_PATIENTID+" = "+
        							patientId, null);
        Patient patient = null;
        if(cursor.moveToFirst()) {
        patient = new Patient();
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
        } else {
        	cursor.close();
          	db.close();
        	return patient;
        }
    	

    	
    }
    public ArrayList<String> getDoctorScheduleDayList(int doctorId) {
    	ArrayList<String> dayList = new ArrayList<String>();
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery("Select Distinct * from "+TABLE_DOCTORSCHEDULE+" where "+COL_SCH_DOCTORID+" = "+doctorId, null);
    	while(cursor.moveToNext()){
    		dayList.add(cursor.getString(2));
    	}
    	return dayList;
    	
    }
    
    public ArrayList<String> getDoctorDayTimeList(int doctorId, String day) {
    	ArrayList<String> dayTimeList = new ArrayList<String>();
    	String sTime, eTime;
    	int sMin, eMin, sHour, eHour;
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery("Select Distinct * from "+TABLE_DOCTORSCHEDULE+" where "+COL_SCH_DOCTORID+" = "+doctorId+
    			" and "+COL_DAY+" = \""+day+"\"", null);
    	while(cursor.moveToNext()){

    		sTime = cursor.getString(3).trim();
    		eTime = cursor.getString(4).trim();
    		String[] sSeparated = sTime.split(":");
    		sHour = Integer.parseInt(sSeparated[0]);
    		sMin = Integer.parseInt(sSeparated[1]);
    		String[] eSeparated = eTime.split(":");
    		eHour = Integer.parseInt(eSeparated[0]);
    		eMin = Integer.parseInt(eSeparated[1]);
    		
    		//8 hours shift logic so if end time go above 24 hour then adding them...
    		if (sHour+8 > 24){
    			eHour = eHour+24; // 
    		} else if (sHour+8 == 24 && sMin>0){
    			eHour = eHour+24;
    		}
    		while(sHour<=eHour){
    			
    			if(sHour<eHour){
    				String st;
    				if(sHour>=24){
    					st = (sHour-24)+":"+sMin;
    				} else {
    					st = sHour+":"+sMin;
    				}
	    			
	    			dayTimeList.add(st);
    				
	    			if(sMin+30<60){
		    			sMin = sMin+30;
		    		} else {
		    			sMin = sMin - 30;
		    			sHour++;
		    		}
    			} else {
    				//checking 30 difference for final 30 minutes
    				if (sMin+30 <= eMin){
    					String st;
        				if(sHour>=24){
        					st = (sHour-24)+":"+sMin;
        				} else {
        					st = sHour+":"+sMin;
        				}
        				dayTimeList.add(st);
        				
    					sMin = sMin+30;
    				} else {
		    			break;
		    		}
    			}
	    		
    		
    		}
     		
    	}
    		
    	return dayTimeList;
    }
    
    //Appointment detail by doctor done by Jignesh patel
    public void addAppointment(int patId, int docId, int deptId, String date, String time){
    	 SQLiteDatabase db = this.getWritableDatabase();
         
         ContentValues values = new ContentValues(); 
         values.put(COL_APP_PATIENTID, patId); 
         values.put(COL_APP_DOCTORID, docId); 
         values.put(COL_APP_DEPARTMENTID, deptId); 
         values.put(COL_DATE, date); 
         values.put(COL_TIME, time);
      
         // Inserting Row
         db.insert(TABLE_APPOINTMENT, null, values);
         db.close(); // Closing database connection
    }
    
    public Appointment getAppointment(int appointmentId) {
    	Appointment apt = null;
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery("Select * from "+TABLE_APPOINTMENT+" where "+COL_APPOINTMENTID+" = "+appointmentId, null);
    	while(cursor.moveToNext()){
    		apt = new Appointment();
    		apt.setAppointmentId(cursor.getInt(0));
    		apt.setPatientId(cursor.getInt(1));
    		apt.setDoctorId(cursor.getInt(2));
    		apt.setDepartmentId(cursor.getInt(3));
    		apt.setDate(cursor.getString(4));
    		apt.setTime(cursor.getString(5));
    		
    		
    	}
    	return apt;
    	
    }
    
    public void addTreatment(Treatment t){
   	 SQLiteDatabase db = this.getWritableDatabase();
        
        ContentValues values = new ContentValues(); 
        values.put(COL_TRE_APPOINTMENTID, t.getAppointmentId()); 
        values.put(COL_SYMPTOMS, t.getSymptoms()); 
        values.put(COL_PRESCRIPTION, t.getPrescription()); 
        values.put(COL_TREATMENT, t.getTreatment()); 
     
        // Inserting Row
        db.insert(TABLE_TREATMENT, null, values);
        db.close(); // Closing database connection
   }
    
    public int getAppointmentId(){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_APPOINTMENT, null);
        cursor.moveToFirst();
    	int id =  cursor.getInt(0);
    	cursor.close();
    	db.close();
    	return id;
    	
    }
    
    //By jignesh patel
    public ArrayList<Appointment> getAllAppointments(int patientId){
    	SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_APPOINTMENT
        		+" where "+COL_APP_PATIENTID+" = "+patientId, null);
        ArrayList<Appointment> listDoctors = new ArrayList<Appointment>();
        Appointment a = null;
        while(cursor.moveToNext()){
        a =	new Appointment();
        a.setAppointmentId(cursor.getInt(0));
        a.setPatientId(cursor.getInt(1));
        a.setDoctorId(cursor.getInt(2));
        a.setDepartmentId(cursor.getInt(3));
        a.setDate(cursor.getString(4));
        a.setTime(cursor.getString(5));
        listDoctors.add(a);
        }
        cursor.close();
    	db.close();
    	return listDoctors;
    }
        
}