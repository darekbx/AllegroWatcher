package com.allegrowatcher;

import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.parsers.ItemsListParser;

import org.junit.Test;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-11.
 */
public class ListItemsParserTest {

    private static final String NS = "namespace";

    @Test
    public void parse() throws Exception {
        SoapObject item = new SoapObject(NS, "item")
                .addProperty("itemId", createPrimitive("itemId", 12345))
                .addProperty("itemTitle", createPrimitive("itemTitle", "title"))
                .addProperty("timeToEnd", createPrimitive("timeToEnd", "1 godz."));
                /*.addProperty("priceInfo", createPriceInfo())
                .addProperty("photosInfo", createPhotoInfo());*/
        SoapObject items = new SoapObject(NS, "itemsList")
                .addSoapObject(item);

        SoapObject response = new SoapObject(NS, "doGetItemsListResponse");
        response.addProperty("itemsList", items);

        List<Item> itemList = new ItemsListParser().parse(response);

        assertNotNull(itemList);
    }

    @Test
    public void get_value() throws Exception {
        SoapObject soapObject = new SoapObject(NS, "test");

        SoapPrimitive primitive = createPrimitive("value", 10);
        soapObject.addProperty("value", primitive);

        assertEquals(new ItemsListParser().getValue(soapObject, "value"), "10");
    }

    @Test
    public void get_photo() throws Exception {
        SoapObject photoInfo = createPhotoInfo();

        String photoUrl = new ItemsListParser().getPhoto(photoInfo, ItemsListParser.PhotoSize.SMALL);

        assertEquals(photoUrl, "http://main.photo.img");
    }

    @Test
    public void get_price() throws Exception {
        SoapObject priceInfo = createPriceInfo();

        String bidding = new ItemsListParser().getPrice(priceInfo, ItemsListParser.PriceType.BIDDING);
        String buyNow = new ItemsListParser().getPrice(priceInfo, ItemsListParser.PriceType.BUY_NOW);
        String withDelivery = new ItemsListParser().getPrice(priceInfo, ItemsListParser.PriceType.WITH_DELIVERY);

        assertEquals(bidding, "27.8");
        assertEquals(buyNow, null);
        assertEquals(withDelivery, "35.00");
    }

    private SoapPrimitive createPrimitive(String name, Object value) {
        return new SoapPrimitive(NS, "value", 10);
    }

    private SoapObject createPriceInfo() {
        SoapObject soapObject = new SoapObject(NS, "price");
        SoapObject photosInfo = new SoapObject(NS, "priceInfo")
                .addSoapObject(createPriceItem(ItemsListParser.PriceType.BIDDING, "27.8"))
                .addSoapObject(createPriceItem(ItemsListParser.PriceType.WITH_DELIVERY, "35.00"));
        soapObject.addProperty("priceInfo", photosInfo);
        return soapObject;
    }

    private SoapObject createPriceItem(ItemsListParser.PriceType type, String value) {
        PropertyInfo photoSize = new PropertyInfo();
        photoSize.setNamespace(NS);
        photoSize.setName("priceType");
        photoSize.setValue(type);

        PropertyInfo photoUrl = new PropertyInfo();
        photoUrl.setNamespace(NS);
        photoUrl.setName("priceValue");
        photoUrl.setValue(value);

        return new SoapObject(NS, "item")
                .addProperty(photoSize)
                .addProperty(photoUrl);
    }

    private SoapObject createPhotoInfo() {
        SoapObject soapObject = new SoapObject(NS, "photo");
        SoapObject photosInfo = new SoapObject(NS, "photosInfo")
                .addSoapObject(createPhotoItem(false, "http://photo.img"))
                .addSoapObject(createPhotoItem(true, "http://main.photo.img"));
        soapObject.addProperty("photosInfo", photosInfo);
        return soapObject;
    }

    private SoapObject createPhotoItem(boolean photoIsMainValue, String photoUrlValue) {
        PropertyInfo photoSize = new PropertyInfo();
        photoSize.setNamespace(NS);
        photoSize.setName("photoSize");
        photoSize.setValue(ItemsListParser.PhotoSize.SMALL.toString());
        photoSize.setType(String.class);

        PropertyInfo photoUrl = new PropertyInfo();
        photoUrl.setNamespace(NS);
        photoUrl.setName("photoUrl");
        photoUrl.setValue(photoUrlValue);

        PropertyInfo photoIsMain = new PropertyInfo();
        photoIsMain.setNamespace(NS);
        photoIsMain.setName("photoIsMain");
        photoIsMain.setValue("" + photoIsMainValue);

        return new SoapObject(NS, "item")
                .addProperty(photoSize)
                .addProperty(photoUrl)
                .addProperty(photoIsMain);
    }
}