package com.allegrowatcher.model;

/**
 * Created by daba on 2016-08-18.
 */

public class Category {
    public int id;
    public String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public com.allegrowatcher.Category toDbCategory() {
        return new com.allegrowatcher.Category(null, id, name);
    }
}
