package com.riachuelo.starwars.api.adapter.out.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SwapiResponseDto {
    private List<SwapiFilmDto> results;
}
