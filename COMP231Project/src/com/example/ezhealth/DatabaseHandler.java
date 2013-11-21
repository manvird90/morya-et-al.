package com.example.ezhealth;

import java.util.ArrayList;

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
    private static final String TABLE_ADMINKEY = "AdminKey";
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

    private static final String TABLE_DOCTOR = "DoctorRegistration";
    private static final String COL_DOCTORID = "DoctorId";//PrimaryKey
    private static final String COL_DOCTORLOGINID = "DoctorLoginId";//Foreign Key
    private static final String COL_EXP = "Experience";
    private static final String COL_SPECIALITY = "Speciality";
    private static final String COL_DOC_DEPARTMENTID = "DepartmentId";

    //Receptionist Registration by Manvir Kaur
    private static final String TABLE_RECEPTIONISTREGISTRATION = "ReceptionistRegistration";
    private static final String COL_RECEPTIONISTID = "ReceptionistId";//PrimaryKey
    private static final String COL_RECEPTIONISTLOGINID = "ReceptionistLoginId";//Foreign Key
    
    //ADMIN REGISTARION BY SACHIN PATEL
    
    //Admin Registration Table
    private static final String TABLE_ADMIN = "AdminReg";
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
               + COL_DOC_DEPARTMENTID + " INTEGER NOT NULL UNIQUE, "
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
                + COL_TIME + " TEXT NOT NULL, "
                + "FOREIGN KEY ("+COL_APP_PATIENTID+") REFERENCES "+TABLE_PATIENT+"("+COL_PATIENTID+"), "
                + "FOREIGN KEY ("+COL_APP_DOCTORID+") REFERENCES "+TABLE_DOCTOR+"("+COL_DOCTORID+"), "
               + "FOREIGN KEY ("+COL_APP_DEPARTMENTID+") REFERENCES "+TABLE_DEPARTMENT+"("+COL_DEPARTMENTID+"))";
        
        db.execSQL(CREATE_APPOINTMENT_TABLE);
        
        String CREATE_AVAILABILITY_TABLE = "CREATE TABLE " + TABLE_AVAILABILITY + "("
                + COL_AVAILABILITYID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + COL_AVA_APPOINTMENTID + " INTEGER NOT NULL, "
        		+ COL_AVA_DATE + " TEXT NOT NULL, "
                + COL_AVA_TIME + " TEXT NOT NULL, "
                + COL_ISAVAILABLE + " TEXT NOT NULL, "
               + "FOREIGN KEY ("+COL_AVA_APPOINTMENTID+") REFERENCES "+TABLE_APPOINTMENT+"("+COL_APPOINTMENTID+"))";
        
        db.execSQL(CREATE_AVAILABILITY_TABLE);
        
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