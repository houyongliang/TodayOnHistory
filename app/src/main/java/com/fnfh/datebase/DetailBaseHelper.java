package com.fnfh.datebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fnfh.Bean.Details;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/20.
 */

public class DetailBaseHelper extends SQLiteOpenHelper{

    private static String NAME="detailbase.db";
    private static String CREATE_TABLE="create table detailbase (_id integer primary key autoincrement ,d_id text,title text ,picUrl text,context text,date text);";

    public DetailBaseHelper(Context context) {
        super(context, NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    /*查询数据*/
    public List<Details> queryDates(){
        List<Details> list=new ArrayList<Details>();
        Details details;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query("detailbase", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String _id=cursor.getString(cursor.getColumnIndex("_id"));
            String d_id=cursor.getString(cursor.getColumnIndex("d_id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String picUrl=cursor.getString(cursor.getColumnIndex("picUrl"));
            String context=cursor.getString(cursor.getColumnIndex("context"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            details=  new Details(_id,d_id,title,context,picUrl,date);
            list.add(details);
        }
        database.close();
        return list;
    }
    /*判断是否有 d_id */
    public boolean isHaveId(String t_id){
        if(t_id==null)
            return false;

        List<Details> list=new ArrayList<Details>();
        Details details;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query("detailbase", null, "d_id=?", new String[]{t_id}, null, null, null);
        while(cursor.moveToNext()){
//            String _id=cursor.getString(cursor.getColumnIndex("_id"));
//            String d_id=cursor.getString(cursor.getColumnIndex("d_id"));
//            String title=cursor.getString(cursor.getColumnIndex("title"));
//            String picUrl=cursor.getString(cursor.getColumnIndex("picUrl"));
//            String context=cursor.getString(cursor.getColumnIndex("context"));
//            String date=cursor.getString(cursor.getColumnIndex("date"));
//            details=  new Details(_id,d_id,title,context,picUrl,date);
//            list.add(details);
            database.close();
            return true;
        }

//        database.close();
//        if(list.size()==0){
//            return false;
//        }
//        return true;
        return false;
    }

    public void insertDates(Details detals){
        if(detals==null){
            return;
        }
        SQLiteDatabase database_add = getWritableDatabase();
        //2.  给表中添加数据 定义sql语句
        ContentValues values=new ContentValues();
        if(detals.getD_id()!=null)
        values.put("d_id", detals.getD_id());
        if(detals.getTitle()!=null)
        values.put("title", detals.getTitle());
        if(detals.getPicUrl()!=null)
        values.put("picUrl", detals.getPicUrl());
        if(detals.getContext()!=null)
        values.put("context", detals.getContext());
        if(detals.getDate()!=null)
        values.put("date", detals.getDate());
        database_add.insert("detailbase", null, values);
        //4. 关闭数据库
        database_add.close();
    }

    public void deltDates(String t_id){
        //1. 通过帮助实例，获取数据库， 此时会调用 帮助类中的 onCreate 方法
        SQLiteDatabase database_delt = getWritableDatabase();
        //2. 定义删除  语句
        database_delt.delete("detailbase", "d_id=?", new String[]{t_id});
        //3. 关闭数据库
        database_delt.close();
    }

}
