package com.allegrowatcher;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void soap() throws Exception {
        SoapTest soapTest = new SoapTest();
        assertEquals("", soapTest.test());
    }
}