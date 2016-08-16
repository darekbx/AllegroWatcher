package com.dao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AllegroDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.allegrowatcher");

        Entity entry = schema.addEntity("AllegroId");
        entry.addIdProperty();
        entry.addLongProperty("allegro_id");

        new DaoGenerator().generateAll(schema, "../../app/src-gen");
    }
}
