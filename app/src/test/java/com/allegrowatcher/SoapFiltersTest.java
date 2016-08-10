package com.allegrowatcher;

import com.allegrowatcher.service.SoapFilters;

import org.junit.Test;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-10.
 */
public class SoapFiltersTest {

    @Test
    public void create_soap_object() throws Exception {

        SoapObject object = SoapFilters.createSoapObject("filter");

        assertEquals(object.getName(), "filter");
        assertEquals(object.getPropertyCount(), 0);
    }

    @Test
    public void create_soap_object_property() throws Exception {

        SoapObject object = SoapFilters.createSoapObject("filter", SoapFilters.createPropertyInfo("id", 1));

        assertEquals(object.getName(), "filter");
        assertEquals(object.getPropertyCount(), 1);
        assertEquals(object.getProperty("id"), 1);
    }

    @Test
    public void create_filter_option() throws Exception {

        PropertyInfo propertyInfo = SoapFilters.createFilterOption("category", "100");

        assertEquals(propertyInfo.getName(), "item");
        assertEquals(propertyInfo.getValue().getClass().getSimpleName(), "SoapObject");

        SoapObject itemSoapObject = (SoapObject)propertyInfo.getValue();
        assertEquals(itemSoapObject.getName(), "item");
        assertEquals(itemSoapObject.getPropertyCount(), 2);
        assertEquals(itemSoapObject.getProperty("filterId"), "category");


        SoapObject filterSoapObject = (SoapObject)itemSoapObject.getProperty("filterValueId");
        assertEquals(filterSoapObject.getName(), "filterValueId");
        assertEquals(filterSoapObject.getPropertyCount(), 1);
        assertEquals(filterSoapObject.getProperty("item"), "100");
    }

    @Test
    public void create_filter_range() throws Exception {

        PropertyInfo propertyInfo = SoapFilters.createFilterRangeOption("price", "20", "100");

        assertEquals(propertyInfo.getName(), "item");
        assertEquals(propertyInfo.getValue().getClass().getSimpleName(), "SoapObject");

        SoapObject itemSoapObject = (SoapObject)propertyInfo.getValue();
        assertEquals(itemSoapObject.getName(), "item");
        assertEquals(itemSoapObject.getPropertyCount(), 2);
        assertEquals(itemSoapObject.getProperty("filterId"), "price");

        SoapObject filterSoapObject = (SoapObject)itemSoapObject.getProperty("filterValueRange");
        assertEquals(filterSoapObject.getName(), "filterValueId");
        assertEquals(filterSoapObject.getPropertyCount(), 2);
        assertEquals(filterSoapObject.getProperty("rangeValueMin"), "20");
        assertEquals(filterSoapObject.getProperty("rangeValueMax"), "100");
    }
}