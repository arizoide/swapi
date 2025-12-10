package com.riachuelo.starwars.api.adapter.out.persistence;

import com.riachuelo.starwars.api.core.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmPersistenceMapper {

    public FilmEntity toEntity(Film film) {
        FilmEntity entity = new FilmEntity();
        entity.setEpisodeId(film.getEpisodeId());
        entity.setTitle(film.getTitle());
        entity.setOpeningCrawl(film.getOpeningCrawl());
        entity.setDirector(film.getDirector());
        entity.setProducer(film.getProducer());
        entity.setReleaseDate(film.getReleaseDate());
        entity.setCharacters(film.getCharacters());
        entity.setPlanets(film.getPlanets());
        entity.setStarships(film.getStarships());
        entity.setVehicles(film.getVehicles());
        entity.setSpecies(film.getSpecies());
        entity.setUrl(film.getUrl());
        entity.setCreated(film.getCreated());
        entity.setEdited(film.getEdited());
        entity.setVersion(film.getVersion());
        return entity;
    }

    public Film toDomain(FilmEntity entity) {
        return Film.builder()
                .episodeId(entity.getEpisodeId())
                .title(entity.getTitle())
                .openingCrawl(entity.getOpeningCrawl())
                .director(entity.getDirector())
                .producer(entity.getProducer())
                .releaseDate(entity.getReleaseDate())
                .characters(entity.getCharacters())
                .planets(entity.getPlanets())
                .starships(entity.getStarships())
                .vehicles(entity.getVehicles())
                .species(entity.getSpecies())
                .url(entity.getUrl())
                .created(entity.getCreated())
                .edited(entity.getEdited())
                .version(entity.getVersion())
                .build();
    }
}
