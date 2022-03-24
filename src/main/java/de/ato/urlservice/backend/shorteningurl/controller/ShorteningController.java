package de.ato.urlservice.backend.shorteningurl.controller;

import de.ato.urlservice.backend.common.exceptions.UrlNotFoundException;
import de.ato.urlservice.backend.common.exceptions.UrlNotValidException;
import de.ato.urlservice.backend.shorteningurl.business.ShorteningService;
import de.ato.urlservice.backend.shorteningurl.model.Url;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.regex.Pattern;

@Validated
@RestController
@RequestMapping("/shortening")
public class ShorteningController {

    private static Logger log = LogManager.getLogger();

    @Autowired
    ShorteningService shorteningService;

    @PostMapping
    public Url convertToShortenUrl(
            @RequestParam(name = "url", required = true)
            String url) throws UrlNotValidException {

        if(!validateUrl(url)){
            throw new UrlNotValidException("Url not valid: " + url);
        }

        //ToDo check if there is any in repro
        Url existingUrl = shorteningService.findByName(url);
        if(existingUrl != null){
            return existingUrl;
        }

        return shorteningService.shorten(Url.builder()
                .originalUrl(url)
                .build());
    }

    @GetMapping()
    public ResponseEntity<Void> getAndRedirect( @RequestParam(name = "shorturl", required = true) String shorturl) throws UrlNotFoundException {

        if(shorturl == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(shorturl))
                .build();
    }

    private boolean validateUrl(String url) {

        if(url == null){
            return false;
        }

        // Regex of valid url
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern p = null;
        try {
            p = Pattern.compile(regex);
        } catch (Exception e) {
            log.error("Error, pattern ist not valid: " + regex, e);
            return false;
        }

        return p.matcher(url).matches();
    }


}
