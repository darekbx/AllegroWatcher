package com.allegrowatcher.service;

import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.enums.StartingTime;
import com.allegrowatcher.service.parsers.ItemsListParser;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapMethods {

    public static List<Item> doGetItemsListRequest(int category, int priceMin, int priceMax, StartingTime startingTime) {
        SoapObject outputSoapObject = SoapEnvelopes.doGetItemsListRequest(category, priceMin, priceMax, startingTime);
        SoapObject object = new SoapService().callSoap(outputSoapObject);
        return new ItemsListParser().parse(object);
    }

    public static List<Item> doGetItemsListRequest(int category, String keyword) {
        SoapObject outputSoapObject = SoapEnvelopes.doGetItemsListRequest(category, keyword);
        SoapObject object = new SoapService().callSoap(outputSoapObject);
        return new ItemsListParser().parse(object);
    }
}