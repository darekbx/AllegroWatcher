package com.allegrowatcher.service;

import com.allegrowatcher.model.Filter;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapEnvelopes {

    public static SoapObject doGetCatsData() {
        SoapObject outputSoapObject = new SoapObject(SoapService.NAMESPACE, "DoGetCatsDataRequest");
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("countryId", SoapService.COUNTRY_CODE));
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("localVersion ", 0));
        outputSoapObject.addPropertyIfValue(SoapFilters.createPropertyInfo("webapiKey", SoapService.WEB_API_KEY));
        return outputSoapObject;
    }

    public static SoapObject doGetItemsListRequest(Filter filter) {
        SoapObject propertyObject = SoapFilters.createSoapObject("filterOptions");
        if (filter.hasCategory()) {
            propertyObject.addProperty(SoapFilters.createFilterOption("category", "" + filter.category.id));
        }
        if (filter.hasKeyword()) {
            propertyObject.addProperty(SoapFilters.createFilterOption("search", filter.keyword));
        }
        if (filter.hasPrice()) {
            propertyObject.addProperty(SoapFilters.createFilterRangeOption("price",
                    "" + filter.priceMin, "" + filter.priceMax));
        }
        return createItemsOutputObject(propertyObject);
    }

    public static SoapObject createItemsOutputObject(SoapObject propertyObject) {
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