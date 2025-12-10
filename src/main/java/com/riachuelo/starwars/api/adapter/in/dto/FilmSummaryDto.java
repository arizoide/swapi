package com.riachuelo.starwars.api.adapter.in.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FilmSummaryDto {
    private final int episodeId;
    private final String title;
    private final String director;
    private final LocalDate releaseDate;
    private final String description;
    private final int version;
}
