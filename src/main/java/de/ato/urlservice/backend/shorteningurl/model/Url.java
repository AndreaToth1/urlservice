package de.ato.urlservice.backend.shortening.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@Builder
public class Url {

    @Id @GeneratedValue
    private Long id;

    @NonNull
    private String originalUrl;

    private String shortenUrl;
}
