package com.example.waitingroom;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;

    // Database Info
    private static final String DATABASE_NAME = "waitingRoomDatabase";

    //Table Names
    private static final String TABLE_DOCTORS = "doctors";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_APPOINTMENTS = "appointments";

    //Appointments Table Columns
    private static final String KEY_APPOINTMENT_ID = "id";
    private static final String KEY_APPOINTMENT_USER_EMAIL = "userEmail";
    private static final String KEY_APPOINTMENT_DOCTOR_ID = "doctorId";
    private static final String KEY_APPOINTMENT_DATE = "appointmentDate";

    //User Table Columns
    private static final String KEY_USER_SURNAME = "surname";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_BIRTHDAY = "birthday";
    private static final String KEY_USER_STREET = "street";
    private static final String KEY_USER_POSTCODE = "postcode";
    private static final String KEY_USER_CITY = "city";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_APPOINTMENT_ID = "appointment_id";

    //Doctors Table Columns
    private static final String KEY_DOCTOR_ID = "doctorId";
    private static final String KEY_DOCTOR_NAME = "doctorName";
    private static final String KEY_DOCTOR_TYPE = "type";
    private static final String KEY_DOCTOR_SEX = "sex";
    private static final String KEY_DOCTOR_STREET = "street";
    private static final String KEY_DOCTOR_POSTCODE = "postcode";
    private static final String KEY_DOCTOR_APPOINTMENT_ID = "appointment_id";

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                    KEY_USER_NAME + " TEXT," + KEY_USER_SURNAME + " TEXT," +
                    KEY_USER_BIRTHDAY + " TEXT," + KEY_USER_STREET + " TEXT," +
                    KEY_USER_POSTCODE + " TEXT," + KEY_USER_CITY + " TEXT," +
                    KEY_USER_EMAIL + " TEXT PRIMARY KEY," + KEY_USER_PASSWORD + " TEXT" +
                ")";

        String CREATE_DOCTORS_TABLE = "CREATE TABLE " + TABLE_DOCTORS +
                "(" +
                    KEY_DOCTOR_ID + " INTEGER PRIMARY KEY," +
                    KEY_DOCTOR_NAME + " TEXT," +
                    KEY_DOCTOR_TYPE + " TEXT," + KEY_DOCTOR_SEX + " TEXT," +
                    KEY_DOCTOR_STREET + " TEXT," + KEY_DOCTOR_POSTCODE + " INTEGER" +
                ")";

        String CREATE_APPOINTMENTS_TABLE = "CREATE TABLE " + TABLE_APPOINTMENTS +
                "(" +
                    KEY_APPOINTMENT_ID + " TEXT PRIMARY KEY," +
                    KEY_APPOINTMENT_USER_EMAIL + " TEXT," + KEY_APPOINTMENT_DOCTOR_ID + " INTEGER," +
                    KEY_APPOINTMENT_DATE + " TEXT" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_DOCTORS_TABLE);
        db.execSQL(CREATE_APPOINTMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_NAME + "'");
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.name);
        values.put(KEY_USER_SURNAME, user.surname);
        values.put(KEY_USER_BIRTHDAY, user.birthday);
        values.put(KEY_USER_STREET, user.street);
        values.put(KEY_USER_POSTCODE, user.postcode);
        values.put(KEY_USER_CITY, user.city);
        values.put(KEY_USER_EMAIL, user.email);
        values.put(KEY_USER_PASSWORD, user.password);

        database.insert(TABLE_USERS, null, values);
    }

    public void addDoctor(Doctor doctor){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DOCTOR_ID, doctor.id);
        values.put(KEY_DOCTOR_NAME, doctor.name);
        values.put(KEY_DOCTOR_TYPE, doctor.doctor_type);
        values.put(KEY_DOCTOR_SEX, doctor.sex);
        values.put(KEY_DOCTOR_STREET, doctor.street);
        values.put(KEY_DOCTOR_POSTCODE, doctor.postcode);

        database.insert(TABLE_DOCTORS, null, values);
}


    public void addAppointment(Appointment appointment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_APPOINTMENT_ID, appointment.id);
        values.put(KEY_APPOINTMENT_USER_EMAIL, appointment.user);
        values.put(KEY_APPOINTMENT_DOCTOR_ID, appointment.doctor);
        values.put(KEY_APPOINTMENT_DATE, appointment.apointment_date);

        db.insert(TABLE_APPOINTMENTS, null, values);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String USERS_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_USERS);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(USERS_SELECT_QUERY, null);
        if (cursor.moveToFirst()){
            do {
                User newUser = new User();
                newUser.name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
                newUser.surname = cursor.getString(cursor.getColumnIndex(KEY_USER_SURNAME));
                newUser.birthday = cursor.getString(cursor.getColumnIndex(KEY_USER_BIRTHDAY));
                newUser.street = cursor.getString(cursor.getColumnIndex(KEY_USER_STREET));
                newUser.postcode = cursor.getString(cursor.getColumnIndex(KEY_USER_POSTCODE));
                newUser.city = cursor.getString(cursor.getColumnIndex(KEY_USER_CITY));
                newUser.email = cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL));
                newUser.password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD));
                users.add(newUser);
            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return users;
    }

    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<>();

        String queryString = "SELECT * FROM doctors";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                Doctor newDoctor = new Doctor();
                newDoctor.id = cursor.getInt(0);
                newDoctor.name = cursor.getString(1);
                newDoctor.doctor_type = cursor.getString(2);
                newDoctor.sex = cursor.getString(3);
                newDoctor.street = cursor.getString(4);
                newDoctor.postcode = cursor.getString(5);
                doctors.add(newDoctor);
            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        return doctors;
    }

    public ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String APPOINTMETS_SELECT_QUERY = String.format("SELECT * FROM %s", TABLE_APPOINTMENTS);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(APPOINTMETS_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()){
                do {
                    Appointment newAppointment = new Appointment();
                    newAppointment.id = cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_ID));
                    newAppointment.user = cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_USER_EMAIL));
                    newAppointment.doctor = cursor.getInt(cursor.getColumnIndex(KEY_APPOINTMENT_DOCTOR_ID));
                    newAppointment.apointment_date = cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_ID));
                    appointments.add(newAppointment);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            System.out.println("Termine könnten nicht zurückgegeben werden");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return appointments;
    }

    public User getUser(String email) {
        SQLiteDatabase db = getWritableDatabase();
        User user = new User();

        String[] selectionArgs = {email};
        String USER_SELECT_QUERY = "SELECT * FROM users WHERE email = ?";
        Cursor cursor = db.rawQuery(USER_SELECT_QUERY, selectionArgs);

        if (cursor.moveToFirst()) {
            user.name = cursor.getString(cursor.getColumnIndex(KEY_USER_NAME));
            user.surname = cursor.getString(cursor.getColumnIndex(KEY_USER_SURNAME));
            user.birthday = cursor.getString(cursor.getColumnIndex(KEY_USER_BIRTHDAY));
            user.street = cursor.getString(cursor.getColumnIndex(KEY_USER_STREET));
            user.postcode = cursor.getString(cursor.getColumnIndex(KEY_USER_POSTCODE));
            user.city = cursor.getString(cursor.getColumnIndex(KEY_USER_CITY));
            user.email = cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL));
            user.password = cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD));
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        db.close();
        return user;
    }

    public Doctor getDoctor(String name){
        SQLiteDatabase db = getWritableDatabase();
        Doctor doctor = new Doctor();
        String DOCTOR_SELECT_QUERY = String.format("SELECT %s FROM %s WHERE %s = ?", KEY_DOCTOR_NAME, TABLE_DOCTORS, KEY_DOCTOR_NAME);
        Cursor cursor = db.rawQuery(DOCTOR_SELECT_QUERY, new String[]{String.valueOf(name)});
        try {
            if (cursor.moveToFirst()) {
                doctor.id = cursor.getInt(cursor.getColumnIndex(KEY_DOCTOR_ID));
                doctor.name = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                doctor.doctor_type = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                doctor.sex = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                doctor.street = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                doctor.postcode = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                db.setTransactionSuccessful();
            }
        } finally {
            if (cursor!= null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return doctor;
    }

    public boolean removeDoctor(Doctor doctor){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DOCTORS, KEY_DOCTOR_NAME + "=" + doctor.name, null) > 0;
    }

    public ArrayList<DoctorAppointmentsList> getDoctorsOf(User user) {
        ArrayList<DoctorAppointmentsList> doctors = new ArrayList<>();
        String[] selectionArgs = {user.email};
        String queryString = "SELECT * FROM " + TABLE_APPOINTMENTS + " JOIN " + TABLE_DOCTORS + " USING (doctorId) " + " WHERE userEmail = ?"; // + " JOIN " + TABLE_DOCTORS + " ON doctors.id = appointments.doctorId"
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, selectionArgs);

        if (cursor.moveToFirst()){
            do {
                DoctorAppointmentsList newDoctor = new DoctorAppointmentsList();
                newDoctor.sex = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_SEX));
                newDoctor.name = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_NAME));
                newDoctor.doctor_type = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_TYPE));
                newDoctor.apointment_date = cursor.getString(cursor.getColumnIndex(KEY_APPOINTMENT_DATE));
                newDoctor.street = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_STREET));
                newDoctor.postcode = cursor.getString(cursor.getColumnIndex(KEY_DOCTOR_POSTCODE));
                doctors.add(newDoctor);
            } while (cursor.moveToNext());
        }

        return doctors;
    }
}

