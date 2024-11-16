// DbAdapter.java
package ex.g1.thamkhao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAdapter {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    public DbAdapter(MainActivity ctx) {
        this.context = ctx;
    }

    public void open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createUser(String name) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        sqLiteDatabase.insert(DATABASE_TABLE, null, inititalValues);
    }

    public boolean deleteUser(long rowId) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    public void deleteAllUsers() {
        sqLiteDatabase.delete(DATABASE_TABLE, null, null);
    }

    public Cursor getAllUsers() {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_NAME}, null, null, null, null, null);
    }
}
