package com.android.truongpq.ngontinh.daos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.truongpq.ngontinh.models.Novel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by truongpq on 30/11/2015.
 */
public class NovelDAO {
    public static final String TABLE_NAME = "Novel";

    public static final String KEY_NAME = "Name";
    public static final String KEY_URL = "Url";
    public static final String KEY_THUMBNAILURL = "ThumbnailUrl";
    public static final String KEY_AUTHOR = "Author";

    SQLiteDatabase db;
    NovelHelper helper;

    public NovelDAO(Context context) {
        helper = new NovelHelper(context);
        helper.createDB();
        db = helper.openDB();
    }

    public List<Novel> getList() {
        List<Novel> novels = new ArrayList<>();

        String selectQuerry = "SELECT " +
                KEY_NAME + ", " +
                KEY_URL + ", " +
                KEY_THUMBNAILURL + ", " +
                KEY_AUTHOR +
                " FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuerry, null);

        if (cursor.moveToFirst()) {
            do {
                Novel novel = new Novel();
                novel.setName(cursor.getString(0));
                novel.setUrl(cursor.getString(1));
                novel.setThumbnailUrl(cursor.getString(2));
                novel.setAuthor(cursor.getString(3));
                novels.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return novels;
    }

    public Novel findByRow(int rowNumber) {
        String selectQuerry = "SELECT " +
                KEY_NAME + ", " +
                KEY_URL + ", " +
                KEY_THUMBNAILURL + ", " +
                KEY_AUTHOR +
                " FROM " + TABLE_NAME + " LIMIT 1 OFFSET " + rowNumber;
        Cursor cursor = db.rawQuery(selectQuerry, null);

        if (cursor != null) cursor.moveToFirst();
        Novel novel = new Novel();
        novel.setName(cursor.getString(0));
        novel.setUrl(cursor.getString(1));
        novel.setThumbnailUrl(cursor.getString(2));
        novel.setAuthor(cursor.getString(3));

        return novel;
    }
}
