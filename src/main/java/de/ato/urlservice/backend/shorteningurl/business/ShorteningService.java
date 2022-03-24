package de.ato.urlservice.backend.shorteningurl.business;

import de.ato.urlservice.backend.shorteningurl.model.Url;
import de.ato.urlservice.backend.shorteningurl.repository.UrlRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Component
public class ShorteningService {
    private static Logger log = LogManager.getLogger();

    private UrlRepository repository;
    private final BaseConversion conversion;

    public ShorteningService(UrlRepository repository, BaseConversion conversion) {
        this.repository = repository;
        this.conversion = conversion;
    }

    public Url shorten(Url url) {
        url.setShortenUrl(convertToShortUrl(url.getOriginalUrl()));
        log.info("create shortened url:" + url);
        return repository.save(url);
    }

    private String convertToShortUrl(String longUrl)  {
        return conversion.encode(longUrl);
    }


    public Url findByName(String origUrl) {
        return repository.findByOriginalUrl(origUrl);
    }

}
