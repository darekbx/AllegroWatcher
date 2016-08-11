package com.allegrowatcher;

import com.allegrowatcher.service.parsers.ItemsListParser;

import org.junit.Test;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-11.
 */
public class ListItemsParserTest {

    @Test
    public void parse() throws Exception {

    }

    @Test
    public void get_value() throws Exception {
        SoapObject soapObject = new SoapObject("namespace", "test");


        SoapPrimitive primitive = new SoapPrimitive("namespace", "value", 10);
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("value");
        propertyInfo.setValue("10");
        propertyInfo.setNamespace("namespace");
        propertyInfo.setType(SoapObject.class);
        soapObject.addProperty(propertyInfo);

        assertEquals(new ItemsListParser().getValue(soapObject, "value"), "10");
    }

    @Test
    public void get_photo() throws Exception {

    }

    @Test
    public void get_price() throws Exception {

    }
}