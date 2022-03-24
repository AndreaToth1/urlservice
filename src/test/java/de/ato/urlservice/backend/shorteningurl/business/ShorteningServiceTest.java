package de.ato.urlservice.backend.shorteningurl.business;

import de.ato.urlservice.backend.shorteningurl.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShorteningServiceTest {

    ShorteningService shorteningService;

    @Mock
    UrlRepository urlRepository;

    @BeforeEach
    public void init(){
        //shorteningService = new ShorteningService(urlRepository);
    }



}