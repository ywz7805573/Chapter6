package com.byted.camp.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.byted.camp.todolist.db.TodoContract.*;
import static com.byted.camp.todolist.db.TodoContract.FeedEntry.COLUMN_PRIORITY;
import static com.byted.camp.todolist.db.TodoContract.FeedEntry.TABLE_NAME;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    // TODO 定义数据库名、版本；创建数据库
    public static final int DATABASE_VERSION = 1;
    public static  final String DATABASE_NAME = "todo.db";
    public static final String EXTRA = "priority";

    public TodoDbHelper(Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(int i = oldVersion;i<newVersion;i++){
            switch (i){
                case 1:
                    try {
                        db.execSQL("ALTER TABLE " + TABLE_NAME
                                + " ADD " + EXTRA + " int");
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

}
