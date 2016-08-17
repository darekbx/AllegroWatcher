package com.allegrowatcher.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.allegrowatcher.AllegroId;
import com.allegrowatcher.DaoMaster;
import com.allegrowatcher.DaoSession;
import com.allegrowatcher.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-16.
 */

public class DataManager {

    private static final String DB = "allegro-id-db";

    public void difference(Context context, List<Item> remoteItems) {
        List<Long> localIds = getStoredIds(context);
        for (Long localId : localIds) {
            for (Item remoteItem : remoteItems) {
                if (remoteItem.id == localId) {
                    remoteItems.remove(remoteItem);
                    break;
                }
            }
        }
    }

    public List<Long> getStoredIds(Context context) {
        DaoSession session = newDaoSession(context);
        List<Long> result = new ArrayList<>();
        Cursor cursor = session.getDatabase().rawQuery("SELECT ALLEGRO_ID FROM ALLEGRO_ID", null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    result.add(cursor.getLong(0));
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        session.getDatabase().close();
        return result;
    }

    public void addAllegroId(Context context, long allegroId) {
        DaoSession session = newDaoSession(context);
        session.getAllegroIdDao().insert(new AllegroId(null, allegroId));
        session.getDatabase().close();
    }

    private DaoSession newDaoSession(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        return master.newSession();
    }
}
