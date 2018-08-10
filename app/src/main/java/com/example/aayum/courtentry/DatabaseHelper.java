package com.example.aayum.courtentry;

/**
 * Created by aayum on 3/8/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "court3.db";
    public static final String TABLE_NAME = "entry";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CASE_NUM";
    public static final String COL_3 = "CASE_NAME";
    public static final String COL_4 = "TITLE";
    public static final String COL_5 = "LAST_DATE";
    public static final String COL_6 = "NEXT_DATE";
    public static final String COL_7 = "TOTAL_FEES";
    public static final String COL_8 = "FEES_PAID";
    public static final String COL_9 = "MOBILE";
    public static final String COL_10 = "COURT_NAME";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CASE_NUM INTEGER,CASE_NAME TEXT,TITLE TEXT,LAST_DATE TEXT,NEXT_DATE TEXT,TOTAL_FEES INTEGER,FEES_PAID INTEGER,MOBILE INTEGER,COURT_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String case_num,String case_name,String title,String last_date,String next_date,String total,String paid,String mobile,String court) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,case_num);
        contentValues.put(COL_3,case_name);
        contentValues.put(COL_4,title);
        contentValues.put(COL_5,last_date);
        contentValues.put(COL_6,next_date);
        contentValues.put(COL_7,total);
        contentValues.put(COL_8,paid);
        contentValues.put(COL_9,mobile);
        contentValues.put(COL_10,court);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public ArrayList<ExportDataModel> getListFromData(){
        ArrayList<ExportDataModel> result = new ArrayList<ExportDataModel>();

        Cursor res = getAllData();
        if(res.getCount()!=0){
            while(res.moveToNext()){

                ExportDataModel es = new ExportDataModel();
                es.setCol1(res.getString(0));
                es.setCol2(res.getString(1));
                es.setCol3(res.getString(2));
                es.setCol4(res.getString(3));
                es.setCol5(res.getString(4));
                es.setCol6(res.getString(5));
                es.setCol7(res.getString(6));
                es.setCol8(res.getString(7));
                es.setCol9(res.getString(8));
                es.setCol10(res.getString(9));
                result.add(es);


            }

        }
        return result;
    }


    public void exportTocsv(ArrayList<ExportDataModel> data){


        File csv = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/com.example.aayum.courtentry/files");
        if(!csv.exists())
            csv.mkdirs();
        File f = new File(csv,"transfer.csv");
        try {

            f.createNewFile();

            CSVWriter csvwrite = new CSVWriter(new FileWriter(f));
            String[] headers = {COL_1,COL_2,COL_3,COL_4,COL_5,COL_6,COL_7,COL_8,COL_9,COL_10};
            csvwrite.writeNext(headers);
            for(ExportDataModel ea: data){


                String[] datadat = {ea.getCol1(),ea.getCol2(),ea.getCol3(),ea.getCol4(),ea.getCol5(),ea.getCol6(),ea.getCol7(),ea.getCol8(),ea.getCol9(),ea.getCol10()};
                csvwrite.writeNext(datadat);


            }
            csvwrite.close();


        }catch(Exception e){
            e.printStackTrace();
        }


    }



    public Cursor getTodaysData(String str){

    SQLiteDatabase db = this.getWritableDatabase();
//    Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE "+COL_6+" = "+str,null);
        Cursor res = db.rawQuery("SELECT * FROM entry WHERE next_date = ?",new String[] {str});
    return res;

    }


    public Cursor getClientDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID,CASE_NAME,TOTAL_FEES,FEES_PAID,MOBILE from"+TABLE_NAME,null);
        return res;
    }


    public Cursor getFeesDetails(String _client){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select total_fees,FEES_PAID from entry WHERE CASE_NAME = ?",new String[]{_client});
       // Cursor res = db.rawQuery("SELECT * FROM entry WHERE next_date = ?",new String[] {str});
        return res;
    }

    public Cursor getCaseDiaData(String _client){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID,CASE_NAME,title,next_date,last_date from "+TABLE_NAME+" WHERE case_name = ?",new String[]{_client});
        return res;
    }


    public Cursor getCaseDataFromNumber(String _client){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID,CASE_NAME,title,next_date,last_date from "+TABLE_NAME+" WHERE case_num = ?",new String[]{_client});
        return res;
    }

    public Cursor getCaseDataFromndoh(String _client){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID,CASE_NAME,title,next_date,last_date from "+TABLE_NAME+" WHERE next_date = ?",new String[]{_client});
        return res;
    }

    public Cursor getCaseDiaCourtData(String _client){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select ID,CASE_NAME,title,next_date,last_date from "+TABLE_NAME+" WHERE court_name = ?",new String[]{_client});
        return res;
    }

    public boolean updateData(String id,String case_num,String case_name,String title,String last_date,String next_date,String total,String paid,String mobile,String court) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,case_num);
        contentValues.put(COL_3,case_name);
        contentValues.put(COL_4,title);
        contentValues.put(COL_5,last_date);
        contentValues.put(COL_6,next_date);
        contentValues.put(COL_7,total);
        contentValues.put(COL_8,paid);
        contentValues.put(COL_9,mobile);
        contentValues.put(COL_10,court);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public boolean updateCaseNum(String id,String case_num){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_2 + " ='" + case_num + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }
    public boolean updateClientName(String id,String name){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_3 + " ='" + name + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }
    public boolean updateTotalFees(String id,String fees){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_7 + " ='" + fees + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }
    public boolean updatePaidFees(String id,String fees){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_8 + " ='" + fees + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public boolean updateldoh(String id,String date){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_5 + " ='" + date + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public boolean updatendoh(String id,String date){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_6 + " ='" + date + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public boolean updatecourt(String id,String court){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_10 + " ='" + court + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public boolean updatephone(String id,String phone){

        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,case_num);
        try {
            db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_9 + " ='" + phone + "' WHERE " + COL_1 + " =" + id);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}