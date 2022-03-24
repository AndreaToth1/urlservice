package de.ato.urlservice.backend.shorteningurl.business;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class BaseConversionTest {

    @Test
    public void testEncode(){
        BaseConversion conversion = new BaseConversion();
        System.out.println(conversion.encode("https://www.google.com"));
    }

}