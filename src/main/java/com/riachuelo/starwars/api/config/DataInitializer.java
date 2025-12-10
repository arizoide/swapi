package com.riachuelo.starwars.api.config;

import com.riachuelo.starwars.api.adapter.out.dto.SwapiFilmDto;
import com.riachuelo.starwars.api.adapter.out.dto.SwapiResponseDto;
import com.riachuelo.starwars.api.core.model.Film;
import com.riachuelo.starwars.api.core.port.out.FilmPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final FilmPersistancePort persistencePort;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SWAPI_URL = "https://swapi.dev/api/films/";

    @Override
    public void run(String... args) {
        try {
            System.out.println("Populando o banco de dados com filmes da SWAPI...");
            SwapiResponseDto response = restTemplate.getForObject(SWAPI_URL, SwapiResponseDto.class);

            if (response != null && response.getResults() != null) {
                for (SwapiFilmDto dto : response.getResults()) {
                    Film film = Film.builder()
                            .episodeId(dto.getEpisodeId())
                            .title(dto.getTitle())
                            .openingCrawl(dto.getOpeningCrawl())
                            .director(dto.getDirector())
                            .producer(dto.getProducer())
                            .releaseDate(LocalDate.parse(dto.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                            .characters(dto.getCharacters())
                            .planets(dto.getPlanets())
                            .starships(dto.getStarships())
                            .vehicles(dto.getVehicles())
                            .species(dto.getSpecies())
                            .url(dto.getUrl())
                            .created(dto.getCreated())
                            .edited(dto.getEdited())
                            .build(); // A versão será gerenciada pelo JPA

                    persistencePort.save(film);
                }
            }
            System.out.println("Banco de dados populado com sucesso: " + persistencePort.findAll().size() + " filmes.");
        } catch (Exception e) {
            System.err.println("Falha ao carregar filmes da SWAPI para o banco de dados. Erro: " + e.getMessage());
        }
    }
}
