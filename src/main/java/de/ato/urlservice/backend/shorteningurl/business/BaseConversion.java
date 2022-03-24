package de.ato.urlservice.backend.shorteningurl.business;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class BaseConversion {

    public String encode(String input){

        String shortUrl = input.substring(0,input.lastIndexOf("."));
        shortUrl = shortUrl.substring(0, shortUrl.length() -2)
                + "." + shortUrl.substring(shortUrl.length() -2, shortUrl.length()) + "/";
        return shortUrl +=  input.hashCode();
    }

}