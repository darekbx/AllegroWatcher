package com.allegrowatcher;

import com.allegrowatcher.model.Item;
import com.allegrowatcher.service.SoapMethods;
import com.allegrowatcher.service.enums.StartingTime;

import java.util.List;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapTest {

    public String test() {

        List<Item> items = SoapMethods.doGetItemsListRequest(16693, 10, 30, StartingTime.DAYS_7);



        return "a";
    }
}