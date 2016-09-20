package com.example.rkilaparthi.crudopeartions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by rkilaparthi on 9/20/2016.
 */
public class DatabaseController extends SQLiteOpenHelper {

    public DatabaseController(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARYKEY,FIRSTNAME TEXT UNIQUE,LASTNAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);

    }

    public void insert_student(String firstname, String lastname) {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("FIRSTNAME", firstname);
        contentvalues.put("LASTNAME", lastname);
        this.getWritableDatabase().insertOrThrow("STUDENTS", "", contentvalues);

    }

    public void delete_student(String firstname) {
        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME='"+firstname+"'",null);
    }
    public void update_student(String old_firstname,String new_firstname){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_firstname+"' WHERE FIRSTNAME='"+old_firstname+"'");
    }
    public void list_all_students(TextView textView){
        Cursor cursor=this.getWritableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }
}
