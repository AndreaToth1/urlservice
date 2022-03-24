package de.ato.urlservice.backend.config;

import de.ato.urlservice.backend.shorteningurl.model.Url;
import de.ato.urlservice.backend.shorteningurl.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UrlRepository repository) {
        return args -> {

            log.info("Preloading " + repository.save(
                    Url.builder()
                            .originalUrl("https://www.daimler.com/karriere/berufserfahrene/direkteinstieg/")
                            .build()));
            log.info("Preloading " + repository.save(
                    Url.builder()
                            .originalUrl("http://www.daimler.com/karriere/jobsuche/")
                            .build()));

        };
    }
}
