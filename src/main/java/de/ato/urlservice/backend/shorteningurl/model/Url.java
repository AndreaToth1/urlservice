package de.ato.urlservice.backend.shorteningurl.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
@ToString
public class Url {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String originalUrl;

    private String shortenUrl;
}
