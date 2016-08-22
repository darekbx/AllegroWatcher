package com.allegrowatcher;

import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.service.SoapEnvelopes;
import com.allegrowatcher.service.SoapService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksoap2.serialization.SoapObject;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by daba on 2016-08-10.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SoapEnvelopesTest {

    @Test
    public void do_get_items_list_request() throws Exception {

        SoapObject object = SoapEnvelopes.doGetItemsListRequest(new Filter(new Category(1000, "test"), 10, 50));

        assertEquals(object.getName(), "DoGetItemsListRequest");
        assertEquals(object.getNamespace(), SoapService.NAMESPACE);
        assertEquals(object.getPropertyCount(), 5);
        assertEquals(object.getProperty("webapiKey"), SoapService.WEB_API_KEY);
        assertEquals(object.getProperty("countryId"), SoapService.COUNTRY_CODE);
        assertEquals(object.getProperty("resultScope"), SoapService.RESULT_SCOPE);
        assertEquals(object.getProperty("resultSize"), SoapService.RESULT_SIZE);
    }

    @Test
    public void do_get_cats_data_request() {
        SoapObject object = SoapEnvelopes.doGetCatsData();

        assertEquals(object.getName(), "DoGetCatsDataRequest");
        assertEquals(object.getNamespace(), SoapService.NAMESPACE);
        assertEquals(object.getPropertyCount(), 3);
        assertEquals(object.getProperty("webapiKey"), SoapService.WEB_API_KEY);
        assertEquals(object.getProperty("countryId"), SoapService.COUNTRY_CODE);
    }
}