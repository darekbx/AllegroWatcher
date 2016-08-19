package com.allegrowatcher;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.allegrowatcher.FilterStorage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FILTER_STORAGE.
*/
public class FilterStorageDao extends AbstractDao<FilterStorage, Long> {

    public static final String TABLENAME = "FILTER_STORAGE";

    /**
     * Properties of entity FilterStorage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Keyword = new Property(1, String.class, "keyword", false, "KEYWORD");
        public final static Property Category_id = new Property(2, Integer.class, "category_id", false, "CATEGORY_ID");
        public final static Property Category_name = new Property(3, String.class, "category_name", false, "CATEGORY_NAME");
        public final static Property Price_min = new Property(4, Integer.class, "price_min", false, "PRICE_MIN");
        public final static Property Price_max = new Property(5, Integer.class, "price_max", false, "PRICE_MAX");
    };


    public FilterStorageDao(DaoConfig config) {
        super(config);
    }
    
    public FilterStorageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FILTER_STORAGE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'KEYWORD' TEXT," + // 1: keyword
                "'CATEGORY_ID' INTEGER," + // 2: category_id
                "'CATEGORY_NAME' TEXT," + // 3: category_name
                "'PRICE_MIN' INTEGER," + // 4: price_min
                "'PRICE_MAX' INTEGER);"); // 5: price_max
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FILTER_STORAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FilterStorage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String keyword = entity.getKeyword();
        if (keyword != null) {
            stmt.bindString(2, keyword);
        }
 
        Integer category_id = entity.getCategory_id();
        if (category_id != null) {
            stmt.bindLong(3, category_id);
        }
 
        String category_name = entity.getCategory_name();
        if (category_name != null) {
            stmt.bindString(4, category_name);
        }
 
        Integer price_min = entity.getPrice_min();
        if (price_min != null) {
            stmt.bindLong(5, price_min);
        }
 
        Integer price_max = entity.getPrice_max();
        if (price_max != null) {
            stmt.bindLong(6, price_max);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FilterStorage readEntity(Cursor cursor, int offset) {
        FilterStorage entity = new FilterStorage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // keyword
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // category_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // category_name
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // price_min
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // price_max
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FilterStorage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setKeyword(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCategory_id(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setCategory_name(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice_min(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setPrice_max(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(FilterStorage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(FilterStorage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
