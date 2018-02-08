package com.weisj.fx.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31 0031.
 */
public class DBUtil extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "search.db";
    private final static int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "SearchRecord";
    private static final String FILED_1 = "name";
    private static final String FILED_2 = "_id";

    public DBUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        System.out.println("new DBUtil");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + FILED_2 + " INTEGER PRIMARY KEY," + FILED_1 + " TEXT" + ");";
        db.execSQL(sql);
//        System.out.println("oncreate创建表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        System.out.println("onUpgrade删除表");
        this.onCreate(db);
    }

    /**
     * 查询表中所有的数据
     *
     * @return
     */
    public List<String> select() {
        Cursor cursor = this.getReadableDatabase()
                .query(TABLE_NAME, null, null, null, null, null, FILED_2+" desc","30");
        List<String> data = new ArrayList<String>();
        if (cursor.moveToFirst()) {
//            System.out.println("当前表中的数据条数：" + cursor.getCount());
            do {
                data.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return data;
    }

    /**
     * 插入一条数据到表中
     *
     * @param name 字段一的值
     */
    public void insert(String name) {
        ContentValues cv = new ContentValues();
        cv.put(FILED_1, name);
        this.getWritableDatabase().insert(TABLE_NAME, null, cv);
        this.getWritableDatabase().close();//关闭数据库对象
    }

    /**
     * 删除表中的若干条数据
     *
     * @param name 一个包含所有要删除数据的"name"字段的数组
     */
    public void delete(String[] name) {
        String where = FILED_1 + " = ?";
        String[] whereValues = name;
        this.getWritableDatabase().delete(TABLE_NAME, where, whereValues);
        this.getWritableDatabase().close();
    }


    /**
     * 清空表中的数据
     */
    public void clean() {
        this.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        System.out.println("clean删除表");
        this.onCreate(this.getWritableDatabase());
        this.getWritableDatabase().close();
    }
}
