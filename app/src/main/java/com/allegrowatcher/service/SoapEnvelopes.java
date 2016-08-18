package com.allegrowatcher.service;

import com.allegrowatcher.service.enums.StartingTime;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapEnvelopes {

    public static SoapObject doGetItemsListRequest(int category, int priceMin, int priceMax, StartingTime startingTime) {
        SoapObject propertyObject = SoapFilters.createSoapObject("filterOptions")
                .addProperty(SoapFilters.createFilterOption("category", "" + category))
                .addProperty(SoapFilters.createFilterRangeOption("price", "" + priceMin, "" + priceMax));
        if (startingTime != null){
            propertyObject.addProperty(SoapFilters.createFilterOption("startingTime", startingTime.toString()));
        }
        return createOutputObject(propertyObject);
    }

    public static SoapObject doGetItemsListRequest(int category, String keyword) {
        SoapObject propertyObject = SoapFilters.createSoapObject("filterOptions")
                .addProperty(SoapFilters.createFilterOption("category", "" + category))
                .addProperty(SoapFilters.createFilterOption("search", keyword));
        return createOutputObject(propertyObject);
    }

    public static SoapObject doGetItemsListRequest(String keyword) {
        SoapObject propertyObject = SoapFilters.createSoapObject("filterOptions")
                .addProperty(SoapFilters.createFilterOption("search", keyword));
        return createOutputObject(propertyObject);
    }

    public static SoapObject createOutputObject(SoapObject propertyObject) {
        final PropertyInfo filterOptions = createFilteroptions();
        filterOptions.setValue(propertyObject);

        SoapObject outputSoapObject = new SoapObject(SoapService.NAMESPACE, "DoGetItemsListRequest");
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("webapiKey", SoapService.WEB_API_KEY));
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("countryId", SoapService.COUNTRY_CODE));
        outputSoapObject.addPropertyIfValue(filterOptions);
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("resultSize", SoapService.RESULT_SIZE));
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("resultScope", SoapService.RESULT_SCOPE));

        return outputSoapObject;
    }

    public static PropertyInfo createFilteroptions() {
        final PropertyInfo filterOptions = new PropertyInfo();
        filterOptions.setName("filterOptions");
        filterOptions.setNamespace(SoapService.NAMESPACE);
        return filterOptions;
    }
}