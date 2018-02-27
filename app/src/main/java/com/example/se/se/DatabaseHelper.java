package com.example.se.se;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_db";
    private static final String TABLE_NAME = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_AGE = "age";
    private static final String KEY_SCHOOL = "school";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " + KEY_FNAME + " TEXT, " + KEY_LNAME +
                " TEXT, " + KEY_AGE + " TEXT, " + KEY_SCHOOL + " TEXT)";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student_Database stud) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, stud.getFname());
        values.put(KEY_LNAME, stud.getLname());
        values.put(KEY_AGE, stud.getAge());
        values.put(KEY_SCHOOL, stud.getSchool());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    Student_Database getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID, KEY_FNAME, KEY_LNAME, KEY_AGE, KEY_SCHOOL}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Student_Database stud = new Student_Database(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4));
        return stud;
    }

    public List<Student_Database> getAllStudents() {
        List<Student_Database> studList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student_Database stud = new Student_Database();
                stud.setId(Integer.parseInt(cursor.getString(0)));
                stud.setFname(cursor.getString(1));
                stud.setLname(cursor.getString(2));
                stud.setAge(Integer.parseInt(cursor.getString(3)));
                stud.setSchool(cursor.getString(4));

                studList.add(stud);
            } while (cursor.moveToNext());
        }

        return studList;
    }

    public int updateStudent(Student_Database stud) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, stud.getFname());
        values.put(KEY_LNAME, stud.getLname());
        values.put(KEY_AGE, stud.getAge());
        values.put(KEY_SCHOOL, stud.getSchool());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] {String.valueOf(stud.getId())});
    }

    public void deleteStudent(Student_Database stud) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[] {String.valueOf(stud.getId())});
        db.close();
    }

    public int getStudentCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
