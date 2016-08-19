package com.allegrowatcher.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by daba on 2016-08-10.
 */

public class SoapService {

    public static final int COUNTRY_CODE = 1;
    public static final int RESULT_SCOPE = 2;
    public static final int RESULT_SIZE = 1000;
    public static final String WEB_API_KEY ="2204e692";
    public static final String NAMESPACE = "https://webapi.allegro.pl/service.php";
    public static final String URL = "https://webapi.allegro.pl/service.php";

    public SoapObject callSoap(SoapObject outputSoapObject) {
        SoapSerializationEnvelope envelope = createEnvelope(outputSoapObject);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call("", envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                throw (SoapFault) envelope.bodyIn;
            } else {
                return (SoapObject) envelope.bodyIn;
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SoapSerializationEnvelope createEnvelope(SoapObject outputSoapObject) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(outputSoapObject);
        return envelope;
    }
}