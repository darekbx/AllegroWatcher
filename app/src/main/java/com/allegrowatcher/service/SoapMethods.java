package com.allegrowatcher.service;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.parsers.CategoriesListParser;
import com.allegrowatcher.service.parsers.ItemsListParser;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapMethods {

    public static List<Item> doGetItemsListRequest(Filter filter) {
        SoapObject outputSoapObject = SoapEnvelopes.doGetItemsListRequest(filter);
        return callAndParseItemsList(outputSoapObject);
    }

    public static List<Category> doGetCatsDataRequest() {
        SoapObject outputSoapObject = SoapEnvelopes.doGetCatsData();
        return callAndParseCatsData(outputSoapObject);
    }

    public static List<Item> callAndParseItemsList(SoapObject outputSoapObject) {
        SoapObject object = new SoapService().callSoap(outputSoapObject);
        return new ItemsListParser().parse(object);
    }

    public static List<Category> callAndParseCatsData(SoapObject outputSoapObject) {
        SoapObject object = new SoapService().callSoap(outputSoapObject);
        return new CategoriesListParser().parse(object);
    }
}