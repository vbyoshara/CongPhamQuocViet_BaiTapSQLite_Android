package com.example.student.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqlDB extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "HocSinh";
    private static final String ID = "id";
    private static final String NAME = "name";

    public SqlDB(Context context) {
        super(context, "HocSinhDB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME +" ("+ ID +" int primary key, " + NAME+ " nvarchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert(HocSinh hs){
        ContentValues ct = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        ct.put(ID,hs.id);
        ct.put(NAME,hs.name);
        db.insert(TABLE_NAME,null,ct);
//        db.execSQL("INSERT INTO HocSinh VALUES ("+ hs.id +", "+ hs.name + ");");
        db.close();
    }

    public HocSinh getHocSinhById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { ID,
                        NAME}, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HocSinh hs = new HocSinh(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        cursor.close();
        db.close();
        return hs;
    }

    public int editHocSinh(HocSinh hs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,hs.getName());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(hs.getId()+ "")});
    }

    public ArrayList<HocSinh> getAllHocSinh() {
        ArrayList<HocSinh> listHocSinh = new ArrayList<HocSinh>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HocSinh student = new HocSinh();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                listHocSinh.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listHocSinh;
    }

    public void deleteHocSinh(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public int getHocSinhCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
