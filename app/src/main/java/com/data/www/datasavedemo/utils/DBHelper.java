package com.data.www.datasavedemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.data.www.datasavedemo.entity.Person;

/**
 * Created by qwh on 2017/9/14.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context ;
    private static final String DB_NAME = "MyDatabase";
    private static final int DB_VERSSION = 1;
    private SQLiteDatabase database;
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, Context context1) {
        super(context, name, factory, version, errorHandler);
        this.context = context1;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person(id integer primary key autoincrement,name varchar(20),address text,phone text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void insert(Person person){
            database = getWritableDatabase();
            database.execSQL("insert into person(name,address,phone) values (?,?,?)",new Object[]{person.name,person.address,person.phone});
    }

    public void delete(String name){
            database = getWritableDatabase();
            database.execSQL("delete from person where name = ?",new Object[]{name});
    }
    public String getAll(){
            database = getWritableDatabase();
//        database.execSQL("select * from person");
//        return null;
        StringBuilder sb = new StringBuilder();
        Cursor cursor = database.rawQuery("select * from person", null);
        while (cursor.moveToNext()) {
            int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始
            String name = cursor.getString(1);//获取第二列的值
            String addr = cursor.getString(2);
            String phone = cursor.getString(3);
            Log.e("result",name+"  "+addr+" "+phone+"\n");
            sb.append(name+"  "+addr+" "+phone+"\n");
        }
        return sb.toString();
    }
    public void delAll(){
        database = getWritableDatabase();
        database.execSQL("delete from person");
    }
}
