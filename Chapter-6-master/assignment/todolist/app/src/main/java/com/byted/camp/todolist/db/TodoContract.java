package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME +
            "(" + FeedEntry._ID  + " INTEGER PRIMARY KEY, "
            +FeedEntry.COLUMN_CONTENT + " TEXT,"
            +FeedEntry.COLUMN_STATE + " INTEGER,"
            +FeedEntry.COLUMN_DATE + " TEXT,"
            +FeedEntry.COLUMN_PRIORITY + " INTEGER)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + FeedEntry.TABLE_NAME;


    private TodoContract() {
    }
    public  static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "note";
        public static final String _ID = "_id";
        public static final String COLUMN_STATE ="state";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_PRIORITY = "priority";
    }

}
