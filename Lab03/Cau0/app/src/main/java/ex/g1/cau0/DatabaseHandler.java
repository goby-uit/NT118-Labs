package ex.g1.cau0;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
    // Contacts Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "NAME";
    private static final String KEY_PH_NO = "PHONE_NUMBER";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_CONTACTS + "("
                + KEY_ID + " INT PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Contact contact, SQLiteDatabase db) {
        ContentValues value = new ContentValues();
        value.put(KEY_ID, contact.getId());
        value.put(KEY_NAME, contact.getName());
        value.put(KEY_PH_NO, contact.getPhoneNumber());
        db.insert(TABLE_CONTACTS, null, value);
    }
    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = getReadableDatabase();
        //@SuppressLint("Recycle")
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + " = ?"
        , new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
            cursor.close();
            return contact;
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO }, null, null,
                null, null, null);
        if(cursor.moveToFirst())
            do{
                Contact contact = new Contact(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                list.add(contact);
            }while(cursor.moveToNext());
        cursor.close();
        return list;
    }

    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
    // Deleting single contact
    public int deleteContact(Contact contact,  SQLiteDatabase db) {
        return db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
}


