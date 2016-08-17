package com.allegrowatcher;

import com.allegrowatcher.service.SoapEnvelopes;
import com.allegrowatcher.service.SoapService;
import com.allegrowatcher.service.enums.StartingTime;

import org.junit.Test;
import org.ksoap2.serialization.SoapObject;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-10.
 */
public class SoapEnvelopesTest {

    @Test
    public void do_get_items_list_request() throws Exception {

        SoapObject object = SoapEnvelopes.doGetItemsListRequest(1000, 10, 50, StartingTime.DAYS_5);

        assertEquals(object.getName(), "DoGetItemsListRequest");
        assertEquals(object.getNamespace(), SoapService.NAMESPACE);
        assertEquals(object.getPropertyCount(), 5);
        assertEquals(object.getProperty("webapiKey"), SoapService.WEB_API_KEY);
        assertEquals(object.getProperty("countryId"), SoapService.COUNTRY_CODE);
        assertEquals(object.getProperty("resultScope"), SoapService.RESULT_SCOPE);
        assertEquals(object.getProperty("resultSize"), SoapService.RESULT_SIZE);
    }
}