package ex.g1.cau1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "StudentManagement";
    // Contacts table name
    private static final String TABLE_STUDENT = "student";
    // Contacts Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_EMAIL = "Email";;
    private static final String KEY_SEX = "SEX";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENT + "("
                + KEY_ID + " INT PRIMARY KEY,"
                + KEY_NAME + " TEXT , "
                + KEY_EMAIL + " TEXT, "
                + KEY_SEX + " INTEGER" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        // Create tables again
        onCreate(db);
    }

    // Adding new student
    public void addStudent(student student, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, student.getId());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_SEX, student.getSex());
        db.insert(TABLE_STUDENT, null, values);
    }

    // Getting single student
    public student getStudent(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID, KEY_NAME, KEY_EMAIL, KEY_SEX },
                KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            student student = new student(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
            cursor.close();
            return student;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }


    // Getting All student
    public ArrayList<student> getAllStudents() {
        ArrayList<student> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID, KEY_NAME, KEY_EMAIL, KEY_SEX }, null, null, null, null, null);
        if(cursor.moveToFirst())
            do{
                 student student = new student(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
                 );
                list.add(student);
            }while(cursor.moveToNext());
        cursor.close();
        return list;
    }

    // Updating single student
    public int updateStudent(student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_EMAIL, student.getEmail());
        values.put(KEY_SEX, student.getSex());

        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }

    // Deleting single student
    public int deleteStudent(student student, SQLiteDatabase db) {
        return db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }


}