package com.riachuelo.starwars.api.core.service;

import com.riachuelo.starwars.api.core.model.Film;
import com.riachuelo.starwars.api.core.port.out.FilmPersistancePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock
    private FilmPersistancePort filmPersistencePort;

    @InjectMocks
    private FilmService filmService;

    private Film mockFilm;

    @BeforeEach
    void setUp() {
        mockFilm = Film.builder()
                .episodeId(4)
                .title("A New Hope")
                .releaseDate(LocalDate.of(1977, 5, 25))
                .version(1)
                .build();
    }

    @Test
    @DisplayName("Deve listar todos os filmes")
    void deveListarTodosOsFilmes() {
        when(filmPersistencePort.findAll()).thenReturn(Collections.singletonList(mockFilm));

        List<Film> films = filmService.listAllFilms();

        assertNotNull(films);
        assertEquals(1, films.size());
        assertEquals(mockFilm.getTitle(), films.get(0).getTitle());
        verify(filmPersistencePort, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve detalhar um filme existente")
    void deveDetalharUmFilmeExistente() {
        when(filmPersistencePort.findByEpisodeId(4)).thenReturn(Optional.of(mockFilm));

        Film film = filmService.getFilmDetails(4);

        assertNotNull(film);
        assertEquals(mockFilm.getEpisodeId(), film.getEpisodeId());
        verify(filmPersistencePort, times(1)).findByEpisodeId(4);
    }

    @Test
    @DisplayName("Deve lançar exceção ao detalhar filme inexistente")
    void deveLancarExcecaoAoDetalharFilmeInexistente() {
        when(filmPersistencePort.findByEpisodeId(99)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            filmService.getFilmDetails(99);
        });
        verify(filmPersistencePort, times(1)).findByEpisodeId(99);
    }

    @Test
    @DisplayName("Deve atualizar a descrição e retornar o filme atualizado")
    void deveAtualizarDescricao() {
        String newOpeningCrawl = "A new description.";
        Film updatedFilm = Film.builder()
                .episodeId(4)
                .title("A New Hope")
                .openingCrawl(newOpeningCrawl)
                .version(2)
                .build();

        when(filmPersistencePort.updateOpeningCrawl(4, newOpeningCrawl)).thenReturn(1);
        when(filmPersistencePort.findByEpisodeId(4)).thenReturn(Optional.of(updatedFilm));

        filmService.updateOpeningCrawl(4, newOpeningCrawl);
        Film film = filmService.getFilmDetails(4);

        assertNotNull(film);
        assertEquals(2, film.getVersion());
        verify(filmPersistencePort, times(1)).updateOpeningCrawl(4, newOpeningCrawl);
        verify(filmPersistencePort, times(1)).findByEpisodeId(4);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um filme que não existe")
    void deveLancarExcecaoAoAtualizarDescricaoDeFilmeInexistente() {
        String newOpeningCrawl = "A new description.";
        when(filmPersistencePort.updateOpeningCrawl(99, newOpeningCrawl)).thenReturn(0);

        assertThrows(NoSuchElementException.class, () -> {
            filmService.updateOpeningCrawl(99, newOpeningCrawl);
        });

        verify(filmPersistencePort, times(1)).updateOpeningCrawl(99, newOpeningCrawl);
        verify(filmPersistencePort, never()).findByEpisodeId(anyInt());
    }
}
