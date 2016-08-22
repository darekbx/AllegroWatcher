package com.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AllegroDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "com.allegrowatcher");

        Entity entry = schema.addEntity("AllegroId");
        entry.addIdProperty();
        entry.addLongProperty("allegro_id");

        Entity filter = schema.addEntity("FilterStorage");
        filter.addIdProperty();
        filter.addStringProperty("keyword");
        filter.addIntProperty("category_id");
        filter.addStringProperty("category_name");
        filter.addIntProperty("price_min");
        filter.addIntProperty("price_max");

        Entity category = schema.addEntity("Category");
        category.addIdProperty();
        category.addIntProperty("category_id");
        category.addStringProperty("category_name");

        new DaoGenerator().generateAll(schema, "../../app/src-gen");
    }
}
