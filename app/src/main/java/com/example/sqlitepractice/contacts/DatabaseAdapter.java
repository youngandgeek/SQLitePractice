package com.example.sqlitepractice.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//
public class DatabaseAdapter {

    //get reference of the database helper class
   static DatabaseHelper dbHelper;
   //database adapter class constructor
    public DatabaseAdapter(Context context){

        dbHelper=new DatabaseHelper(context);
    }
    //insert to database method
    //save the entry as long value and pass the model class name
    //pass the user class as argument
    public long addEntry(ContactsModelClass entry){
        //open database in writeable mood by dbhelper class
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //creating row in db under the columns to insert the data in them
        ContentValues contentValues=new ContentValues();
        //row.put(db helper class.attribute(first column name),model class.method(get name)
        contentValues.put(DatabaseHelper.COL_NAME,entry.getName());
        contentValues.put(DatabaseHelper.COL_NUMBER,entry.getNumber());

        //save the insertion statement in id because if you wanted to check later if it's inserted or not

        long id=db.insert(DatabaseHelper.TABLE_PHONE_CONTACTS,null,contentValues);

        return id;
    }

    //retrieve data from sqlite db into recycler view

    public ArrayList<ContactsModelClass> readContacts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " +DatabaseHelper.TABLE_PHONE_CONTACTS, null);

        // on below line we are creating a new array list.
        ArrayList<ContactsModelClass> contactsModelClassArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contactsModelClassArrayList.add(new ContactsModelClass(cursorContacts.getString(1),
                        cursorContacts.getString(2)));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contactsModelClassArrayList;
    }

    /**
    //retrieve data from sqlite method

    public ContactsModelClass getEntry(){
        ContactsModelClass entry=null;
        //cursor to curse through table and retrieve data
        Cursor c;
        //access db as readable
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //array to get the data
        String []columns={DatabaseHelper.COL_NAME,DatabaseHelper.COL_NUMBER};
        //retrieve data by cursor
  c=db.query(DatabaseHelper.TABLE_PHONE_CONTACTS,columns,null,null,null,null,null);
   //to loop through the cursor,as long as there's next
        while (c.moveToNext()){
            //c.getstring(first column index number),
            entry=new ContactsModelClass(c.getString(0),c.getString(1));
        }
   return entry;
    }
**/
//Helper class : creation of the database and the upgrade(inserting,updating , editing inside tables)
    public static class DatabaseHelper extends SQLiteOpenHelper{

        //declare the database 1-version,2-name,3-table name,4-columns name.

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="PHONE_CONTACTS.db";
    private static final String TABLE_PHONE_CONTACTS="PHONE_CONTACTS";
    //first column in table
    private static final String COL_UID="_id";
    private static final String COL_NAME="name";
    private static final String COL_NUMBER="number";

    /**creation of database statement with id primary key , (column name + datatype)
     ,we're passing that string value to execSQL method in OnCreate to create the database
     **/
    private static final String CREATE_PHONE_CONTACTS_TABLE = "CREATE TABLE "+ TABLE_PHONE_CONTACTS +
            " (" + COL_UID + " INTEGER PRIMARY KEY AUTOINCREMENT,  "
            + COL_NAME + " TEXT, "
            + COL_NUMBER + " TEXT ); ";



        //default constructor of helper class
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        //you can change the reference name to db
        public void onCreate(SQLiteDatabase db) {
            //pass the string value of creation statement
            db.execSQL(CREATE_PHONE_CONTACTS_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

            //we can check if table exists > drop it and create it again
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE_CONTACTS);
            onCreate(db);
        }
    }
}
