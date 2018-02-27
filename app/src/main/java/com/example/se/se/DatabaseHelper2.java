package com.example.se.se;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quiz_db";
    private static final String TABLE_NAME = "quiz";
    private static final String KEY_ID = "id";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_OPTION1 = "option1";
    private static final String KEY_OPTION2 = "option2";
    private static final String KEY_OPTION3 = "option3";
    private static final String KEY_OPTION4 = "option4";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUIZ_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " + KEY_QUESTION + " TEXT, " + KEY_OPTION1 +
                " TEXT, " + KEY_OPTION2 + " TEXT, " + KEY_OPTION3 + " TEXT, " + KEY_OPTION4 + " TEXT)";
        db.execSQL(CREATE_QUIZ_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addQuestion(CSVReader quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, quiz.getQuestion());
        values.put(KEY_OPTION1, quiz.getOption1());
        values.put(KEY_OPTION2, quiz.getOption2());
        values.put(KEY_OPTION3, quiz.getOption3());
        values.put(KEY_OPTION4, quiz.getOption4());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    CSVReader getQuestion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID, KEY_QUESTION, KEY_OPTION1, KEY_OPTION2, KEY_OPTION3, KEY_OPTION4}, KEY_ID + "=?",
            new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        CSVReader quiz = new CSVReader(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return quiz;
    }

    public List<CSVReader> getAllQuestions() {
        List<CSVReader> quizList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CSVReader quiz = new CSVReader();
                quiz.setId(Integer.parseInt(cursor.getString(0)));
                quiz.setQuestion(cursor.getString(1));
                quiz.setOption1(cursor.getString(2));
                quiz.setOption2(cursor.getString(3));
                quiz.setOption3(cursor.getString(4));
                quiz.setOption4(cursor.getString(5));

                quizList.add(quiz);
            } while (cursor.moveToNext());
        }

        return quizList;
    }

    public int updateQuestion(CSVReader quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, quiz.getQuestion());
        values.put(KEY_OPTION1, quiz.getOption1());
        values.put(KEY_OPTION2, quiz.getOption2());
        values.put(KEY_OPTION3, quiz.getOption3());
        values.put(KEY_OPTION4, quiz.getOption4());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[] {String.valueOf(quiz.getId())});
    }

    public void deleteQuestion(CSVReader quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=?",
                new String[] {String.valueOf(quiz.getId())});
        db.close();
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    public int getQuestionCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
