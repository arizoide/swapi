package com.riachuelo.starwars.api.core.port.in;

import com.riachuelo.starwars.api.core.model.Film;

import java.util.List;

public interface FilmManagementPort {
    List<Film> listAllFilms();
    Film getFilmDetails(int episodeId);
    Integer updateOpeningCrawl(int episodeId, String newOpeningCrawl);
}
