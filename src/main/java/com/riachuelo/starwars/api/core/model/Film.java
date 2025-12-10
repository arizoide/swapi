package com.riachuelo.starwars.api.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class Film {

    // Core properties from SWAPI
    private final int episodeId;
    private final String title;
    private String openingCrawl; // Changed to be mutable
    private final String director;
    private final String producer;
    private final LocalDate releaseDate;
    private final List<String> characters;
    private final List<String> planets;
    private final List<String> starships;
    private final List<String> vehicles;
    private final List<String> species;
    private final String url;
    private final String created;
    private final String edited;

    private int version;
}
