 package com.riachuelo.starwars.api.adapter.in.mapper;

import com.riachuelo.starwars.api.adapter.in.dto.FilmDetailsDto;
import com.riachuelo.starwars.api.adapter.in.dto.FilmSummaryDto;
import com.riachuelo.starwars.api.core.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmSummaryDto toSummaryDto(Film film) {
        return FilmSummaryDto.builder()
                .episodeId(film.getEpisodeId())
                .title(film.getTitle())
                .director(film.getDirector())
                .releaseDate(film.getReleaseDate())
                .description(film.getOpeningCrawl())
                .version(film.getVersion())
                .build();
    }

    public FilmDetailsDto toDetailsDto(Film film) {
        return FilmDetailsDto.builder()
                .episodeId(film.getEpisodeId())
                .title(film.getTitle())
                .openingCrawl(film.getOpeningCrawl())
                .director(film.getDirector())
                .producer(film.getProducer())
                .releaseDate(film.getReleaseDate())
                .characters(film.getCharacters())
                .planets(film.getPlanets())
                .starships(film.getStarships())
                .vehicles(film.getVehicles())
                .species(film.getSpecies())
                .url(film.getUrl())
                .created(film.getCreated())
                .edited(film.getEdited())
                .version(film.getVersion())
                .build();
    }
}
