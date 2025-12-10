package com.riachuelo.starwars.api.adapter.in.mapper;

import com.riachuelo.starwars.api.adapter.in.dto.FilmDetailsDto;
import com.riachuelo.starwars.api.adapter.in.dto.FilmSummaryDto;
import com.riachuelo.starwars.api.core.model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FilmMapperTest {

    private FilmMapper filmMapper;
    private Film film;

    @BeforeEach
    void setUp() {
        filmMapper = new FilmMapper();

        film = Film.builder()
                .episodeId(4)
                .title("A New Hope")
                .openingCrawl("It is a period of civil war...")
                .director("George Lucas")
                .producer("Gary Kurtz, Rick McCallum")
                .releaseDate(LocalDate.of(1977, 5, 25))
                .characters(Arrays.asList("Luke Skywalker", "Darth Vader"))
                .planets(Arrays.asList("Tatooine", "Alderaan"))
                .starships(Arrays.asList("X-wing", "TIE Advanced x1"))
                .vehicles(Arrays.asList("Sand Crawler", "T-16 skyhopper"))
                .species(Arrays.asList("Human", "Droid"))
                .url("https://swapi.dev/api/films/1/")
                .created("2014-12-10T14:23:31.880000Z")
                .edited("2014-12-20T19:49:45.256000Z")
                .version(1)
                .build();
    }

    @Test
    @DisplayName("Deve mapear Film para FilmSummaryDto corretamente")
    void deveMapearParaSummaryDto() {
        FilmSummaryDto summaryDto = filmMapper.toSummaryDto(film);

        assertThat(summaryDto).isNotNull();
        assertThat(summaryDto.getEpisodeId()).isEqualTo(film.getEpisodeId());
        assertThat(summaryDto.getTitle()).isEqualTo(film.getTitle());
        assertThat(summaryDto.getDirector()).isEqualTo(film.getDirector());
        assertThat(summaryDto.getReleaseDate()).isEqualTo(film.getReleaseDate());
    }

    @Test
    @DisplayName("Deve mapear Film para FilmDetailsDto corretamente")
    void deveMapearParaDetailsDto() {
        FilmDetailsDto detailsDto = filmMapper.toDetailsDto(film);

        assertThat(detailsDto).isNotNull();
        assertThat(detailsDto.getEpisodeId()).isEqualTo(film.getEpisodeId());
        assertThat(detailsDto.getTitle()).isEqualTo(film.getTitle());
        assertThat(detailsDto.getOpeningCrawl()).isEqualTo(film.getOpeningCrawl());
        assertThat(detailsDto.getDirector()).isEqualTo(film.getDirector());
        assertThat(detailsDto.getProducer()).isEqualTo(film.getProducer());
        assertThat(detailsDto.getReleaseDate()).isEqualTo(film.getReleaseDate());
        assertThat(detailsDto.getCharacters()).isEqualTo(film.getCharacters());
        assertThat(detailsDto.getPlanets()).isEqualTo(film.getPlanets());
        assertThat(detailsDto.getStarships()).isEqualTo(film.getStarships());
        assertThat(detailsDto.getVehicles()).isEqualTo(film.getVehicles());
        assertThat(detailsDto.getSpecies()).isEqualTo(film.getSpecies());
        assertThat(detailsDto.getUrl()).isEqualTo(film.getUrl());
        assertThat(detailsDto.getCreated()).isEqualTo(film.getCreated());
        assertThat(detailsDto.getEdited()).isEqualTo(film.getEdited());
    }
}
