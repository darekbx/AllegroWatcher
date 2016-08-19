package com.allegrowatcher.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.allegrowatcher.AllegroId;
import com.allegrowatcher.DaoMaster;
import com.allegrowatcher.DaoSession;
import com.allegrowatcher.FilterStorage;
import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-16.
 */

public class DataManager {

    private static final String DB = "allegro-id-db";

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public void difference(List<Item> remoteItems) {
        List<Long> localIds = getStoredIds();
        for (Long localId : localIds) {
            for (Item remoteItem : remoteItems) {
                if (remoteItem.id == localId) {
                    remoteItems.remove(remoteItem);
                    break;
                }
            }
        }
    }

    public List<Long> getStoredIds() {
        DaoSession session = newDaoSession();
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

    public void addAllegroId(long allegroId) {
        DaoSession session = newDaoSession();
        session.getAllegroIdDao().insert(new AllegroId(null, allegroId));
        session.getDatabase().close();
    }

    public void addFilter(Filter filter) {
        DaoSession session = newDaoSession();
        session.getFilterStorageDao().insert(filter.toFilterStorage());
        session.getDatabase().close();
    }

    public void deleteFilter(long id) {
        DaoSession session = newDaoSession();
        session.getFilterStorageDao().deleteByKey(id);
        session.getDatabase().close();
    }

    public List<Filter> getFilters() {
        DaoSession session = newDaoSession();
        List<FilterStorage> filterStorages = session.getFilterStorageDao().loadAll();
        session.getDatabase().close();

        List<Filter> filters = new ArrayList<>(filterStorages.size());
        for (FilterStorage filterStorage : filterStorages) {
            filters.add(Filter.fromFilterStorage(filterStorage));
        }
        return filters;
    }

    public void loadInitialFilters() {
        DaoSession session = newDaoSession();
        for (Filter filter : filters) {
            session.getFilterStorageDao().insert(filter.toFilterStorage());
        }
        session.getDatabase().close();
    }

    private DaoSession newDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        return master.newSession();
    }

    private List<Filter> filters = new ArrayList<Filter>() {
        { add(new Filter(new Category(16693, "Widelce sztywne"), 0, 260)); }
        { add(new Filter(new Category(16447, "Ramy"), 0, 500)); }
        { add(new Filter("Trialowy", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialowka", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialowa", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialu", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trial", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Echo", new Category(16420, "Rowery"))); }
        { add(new Filter("Echo", new Category(16416, "Części"))); }
        { add(new Filter("Koxx", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Monty", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialtech", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Zoo", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("TryAll", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Because", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("DaBomb", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Da Bomb", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Etch A Sketch")); }
        { add(new Filter("Learning Resources")); }
    };
}
