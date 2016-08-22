package com.allegrowatcher.service.parsers;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

/**
 * Created by daba on 2016-08-22.
 */

public class BaseParser {

    public String getValue(SoapObject soapItem, String propertyName) {
        SoapPrimitive itemPrimitive = (SoapPrimitive) soapItem.getPrimitiveProperty(propertyName);
        return itemPrimitive.getValue().toString();
    }

    public String getPhoto(SoapObject soapItem, ItemsListParser.PhotoSize size) {
        SoapObject itemSoapObject = (SoapObject) soapItem.getProperty("photosInfo");
        for (int i = 0, count = itemSoapObject.getPropertyCount(); i < count; i++) {
            SoapObject subItemSoapObject = (SoapObject) itemSoapObject.getProperty(i);
            if (subItemSoapObject.getProperty("photoSize").toString().equals(size.toString())
                    && subItemSoapObject.getProperty("photoIsMain").toString().equals("true")) {
                return subItemSoapObject.getProperty("photoUrl").toString();
            }
        }
        return null;
    }

    public String getPrice(SoapObject soapItem, ItemsListParser.PriceType type) {
        SoapObject itemSoapObject = (SoapObject) soapItem.getProperty("priceInfo");
        for (int i = 0, count = itemSoapObject.getPropertyCount(); i < count; i++) {
            SoapObject subItemSoapObject = (SoapObject) itemSoapObject.getProperty(i);
            if (subItemSoapObject.getProperty("priceType").toString().equals(type.toString())) {
                return subItemSoapObject.getProperty("priceValue").toString();
            }
        }
        return null;
    }
}
