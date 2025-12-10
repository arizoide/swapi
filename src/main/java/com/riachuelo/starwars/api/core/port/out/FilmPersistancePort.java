package com.riachuelo.starwars.api.core.port.out;

import com.riachuelo.starwars.api.core.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmPersistancePort {
    List<Film> findAll();
    Optional<Film> findByEpisodeId(int episodeId);
    Film save(Film film);
    Integer updateOpeningCrawl(int episodeId, String newOpeningCrawl);
}

