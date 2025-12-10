package com.riachuelo.starwars.api.adapter.in.controller;

import com.riachuelo.starwars.api.adapter.in.dto.FilmSummaryDto;
import com.riachuelo.starwars.api.adapter.in.mapper.FilmMapper;
import com.riachuelo.starwars.api.adapter.in.dto.FilmDetailsDto;
import com.riachuelo.starwars.api.core.port.in.FilmManagementPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmManagementPort filmManagementPort;
    private final FilmMapper filmMapper;

    public record OpeningCrawlUpdateDto(String description) {
    }

    public FilmController(FilmManagementPort filmManagementPort, FilmMapper filmMapper) {
        this.filmManagementPort = filmManagementPort;
        this.filmMapper = filmMapper;
    }

    @GetMapping
    public List<FilmSummaryDto> getAllFilms() {
        return filmManagementPort.listAllFilms().stream()
                .map(filmMapper::toSummaryDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{episodeId}")
    public FilmSummaryDto getFilmSummary(@PathVariable int episodeId) {
        var film = filmManagementPort.getFilmDetails(episodeId);
        return filmMapper.toSummaryDto(film);
    }

    @GetMapping("/{episodeId}/details")
    public FilmDetailsDto getFilmDetails(@PathVariable int episodeId) {
        var film = filmManagementPort.getFilmDetails(episodeId);
        return filmMapper.toDetailsDto(film);
    }

    @PutMapping("/{episodeId}/description")
    public FilmDetailsDto updateOpeningCrawl(
            @PathVariable int episodeId,
            @RequestBody OpeningCrawlUpdateDto updateDto) {

        var updatedFilmId = filmManagementPort.updateOpeningCrawl(
                episodeId,
                updateDto.description()
        );

        if (updatedFilmId == null) {
            throw new NoSuchElementException("Filme com ID " + episodeId + " não encontrado.");
        }

        var updatedFilm = filmManagementPort.getFilmDetails(episodeId);
        return filmMapper.toDetailsDto(updatedFilm);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFoundException(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
