package com.example.passwordkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "pokmadb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "passtable";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String ENTITI_COL = "entiti";

    // below variable id for our course duration column.
    private static final String USERNAME_COL = "username";

    // below variable for our course description column.
    private static final String PASSWORD_COL = "password";

    private static final String DESCRIPTION_COL = "description";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ENTITI_COL + " TEXT,"
                + USERNAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";



        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<PakmadModal> readPassTable() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<PakmadModal> pakMadModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                pakMadModalArrayList.add(new PakmadModal(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4)));
                // cursorCourses.getInt(5)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();

        if (pakMadModalArrayList.size() < 1){
            pakMadModalArrayList.add(new PakmadModal("No Data",
                    "Found ",
                    "in",
                    "database"));
        }
        return pakMadModalArrayList;
    }

    public long addNewEntiti(String entiti, String username, String password, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ENTITI_COL, entiti);
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(DESCRIPTION_COL, description);

        try {
            long rValue = db.insert(TABLE_NAME, null, values);
            return rValue;
        } catch (Exception e) {
            Log.e("DBHandler", "Error adding new entity: " + e.getMessage());
            return -1; // Return a value indicating failure
        } finally {
            db.close(); // Close the database in a finally block
        }
    }


    public int updateEntiti(String oriEntiti, String entitiStr, String usernameStr,
                            String passwordStr, String descriptionStr) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ENTITI_COL, entitiStr);
        values.put(USERNAME_COL, usernameStr);
        values.put(PASSWORD_COL, passwordStr);
        values.put(DESCRIPTION_COL, descriptionStr);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        int rValue = db.update(TABLE_NAME, values, "entiti=?", new String[]{oriEntiti});
        db.close();
        return rValue;
    }

    public int deleteEntiti(String oriEntiti){
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        int rValue = db.delete(TABLE_NAME,"entiti=?",new String[]{oriEntiti});
        return rValue;
    }
}