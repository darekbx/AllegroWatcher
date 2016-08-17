package com.allegrowatcher.service;

import com.allegrowatcher.service.enums.StartingTime;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapEnvelopes {

    public static SoapObject doGetItemsListRequest(int category, int priceMin, int priceMax, StartingTime startingTime) {
        SoapObject outputSoapObject = new SoapObject(SoapService.NAMESPACE, "DoGetItemsListRequest");

        final PropertyInfo rangeProperty = new PropertyInfo();
        rangeProperty.setName("filterOptions");
        rangeProperty.setNamespace(SoapService.NAMESPACE);

        SoapObject propertyObject = SoapFilters.createSoapObject("filterOptions")
                .addProperty(SoapFilters.createFilterOption("category", "" + category))
                .addProperty(SoapFilters.createFilterRangeOption("price", "" + priceMin, "" + priceMax));
        if (startingTime != null){
            propertyObject.addProperty(SoapFilters.createFilterOption("startingTime", startingTime.toString()));
        }
        rangeProperty.setValue(propertyObject);

        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("webapiKey", SoapService.WEB_API_KEY));
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("countryId", SoapService.COUNTRY_CODE));
        outputSoapObject.addPropertyIfValue(rangeProperty);
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("resultScope", SoapService.RESULT_SCOPE));

        return outputSoapObject;
    }
}