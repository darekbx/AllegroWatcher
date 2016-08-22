package com.allegrowatcher.service;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * Created by daba on 2016-08-10.
 */
public class SoapFilters {

    public static PropertyInfo createFilterRangeOption(final String filterId, final String valueMin, final String valueMax) {
        PropertyInfo filterProperty = createPropertyInfo("filterId", filterId);
        PropertyInfo rangeMinProperty = createPropertyInfo("rangeValueMin", valueMin);
        PropertyInfo rangeMaxProperty = createPropertyInfo("rangeValueMax", valueMax);

        SoapObject filterRangeObject = createSoapObject("filterValueId", rangeMinProperty, rangeMaxProperty);
        PropertyInfo filterRangeProperty = createPropertyInfo("filterValueRange", filterRangeObject);

        SoapObject itemObject = createSoapObject("item", filterProperty, filterRangeProperty);
        PropertyInfo itemProperty = createPropertyInfo("item", itemObject);
        return itemProperty;
    }

    public static PropertyInfo createFilterOption(final String filterId, final String value) {
        PropertyInfo filterProperty = createPropertyInfo("filterId", filterId);
        PropertyInfo itemProperty = createPropertyInfo("item", value);

        SoapObject filterValueObject = createSoapObject("filterValueId", itemProperty);
        PropertyInfo filterValueProperty = createPropertyInfo("filterValueId", filterValueObject);

        SoapObject itemObject = createSoapObject("item", filterProperty, filterValueProperty);
        PropertyInfo itemFilterProperty = createPropertyInfo("item", itemObject);
        return itemFilterProperty;
    }

    public static PropertyInfo createPropertyInfo(String name, Object value) {
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(name);
        propertyInfo.setValue(value);
        propertyInfo.setNamespace(SoapService.NAMESPACE);
        return propertyInfo;
    }

    public static SoapObject createSoapObject(String name) {
        return new SoapObject(SoapService.NAMESPACE, name);
    }

    public static SoapObject createSoapObject(String name, PropertyInfo... properties) {
        SoapObject soapObject = new SoapObject(SoapService.NAMESPACE, name);
        for (PropertyInfo propertyInfo : properties) {
            soapObject.addProperty(propertyInfo);
        }
        return soapObject;
    }
}
