package de.ato.urlservice.backend.shorteningurl.repository;

import de.ato.urlservice.backend.shorteningurl.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByOriginalUrl(String originalUrl);
}
