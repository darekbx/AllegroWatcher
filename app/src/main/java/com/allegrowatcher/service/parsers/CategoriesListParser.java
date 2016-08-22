package com.allegrowatcher.service.parsers;

import com.allegrowatcher.model.Category;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-22.
 */

public class CategoriesListParser extends BaseParser {

    public List<Category> parse(SoapObject object) {
        List<Category> categoriesList = new ArrayList<>();

        if (object.hasProperty("catsList")) {
            Object catsListObject = object.getProperty("catsList");
            if (catsListObject != null && catsListObject instanceof SoapObject) {
                SoapObject itemsList = (SoapObject) catsListObject;
                int itemsCount = itemsList.getPropertyCount();
                for (int i = 0; i < itemsCount; i++) {
                    Object itemObject = itemsList.getProperty(i);
                    if (itemObject != null && itemObject instanceof SoapObject) {
                        SoapObject soapItem = (SoapObject) itemObject;
                        Category item = new Category(
                                Integer.parseInt(getValue(soapItem, "catId")),
                                getValue(soapItem, "catName")
                        );
                        categoriesList.add(item);
                    }
                }
            }
        }
        return categoriesList;
    }
}