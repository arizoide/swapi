package com.riachuelo.starwars.api.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class FilmDetailsDto {
    private final int episodeId;
    private final String title;
    private final String openingCrawl;
    private final String director;
    private final String producer;
    private final LocalDate releaseDate;

    //TODO: estas propriedades podem se tornar objetos, se num futuro fizer sentido
    private final List<String> characters;
    private final List<String> planets;
    private final List<String> starships;
    private final List<String> vehicles;
    private final List<String> species;

    private final String url;
    private final String created;
    private final String edited;

    private final int version;
}
