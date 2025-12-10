package com.riachuelo.starwars.api.adapter.in.controller;

import com.riachuelo.starwars.api.adapter.in.dto.FilmDetailsDto;
import com.riachuelo.starwars.api.adapter.in.mapper.FilmMapper;
import com.riachuelo.starwars.api.core.model.Film;
import com.riachuelo.starwars.api.core.port.in.FilmManagementPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmController.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmManagementPort filmManagementPort;

    @MockBean
    private FilmMapper filmMapper;

    private Film mockFilm;

    @BeforeEach
    void setUp() {
        mockFilm = Film.builder()
                .episodeId(4)
                .title("A New Hope")
                .director("George Lucas")
                .releaseDate(LocalDate.of(1977, 5, 25))
                .build();
    }

    @Test
    @DisplayName("GET /films/{id}/details - Deve retornar 200 e os detalhes do filme")
    void deveRetornarDetalhesDoFilme() throws Exception {
        when(filmManagementPort.getFilmDetails(4)).thenReturn(mockFilm);
        
        FilmDetailsDto detailsDto = FilmDetailsDto.builder()
                .episodeId(mockFilm.getEpisodeId())
                .title(mockFilm.getTitle())
                .openingCrawl(mockFilm.getOpeningCrawl())
                .director(mockFilm.getDirector())
                .producer(mockFilm.getProducer())
                .releaseDate(mockFilm.getReleaseDate())
                .build();

        when(filmMapper.toDetailsDto(mockFilm)).thenReturn(detailsDto);

        mockMvc.perform(get("/api/v1/films/4/details"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.episodeId").value(4))
                .andExpect(jsonPath("$.title").value("A New Hope"));
    }

    @Test
    @DisplayName("GET /films/{id}/details - Deve retornar 404 quando o filme não existe")
    void deveRetornar404AoBuscarFilmeInexistente() throws Exception {
        when(filmManagementPort.getFilmDetails(anyInt())).thenThrow(new NoSuchElementException("Filme não encontrado."));

        mockMvc.perform(get("/api/v1/films/99/details"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /films/{id}/description - Deve retornar 200 e o filme atualizado")
    void deveAtualizarDescricaoEretornarFilme() throws Exception {
        String requestBody = "{\"description\":\"New description\"}";
        
        FilmDetailsDto updatedDetailsDto = FilmDetailsDto.builder()
                .episodeId(mockFilm.getEpisodeId())
                .title(mockFilm.getTitle())
                .openingCrawl("New description")
                .director(mockFilm.getDirector())
                .producer(mockFilm.getProducer())
                .releaseDate(mockFilm.getReleaseDate())
                .build();

        when(filmManagementPort.updateOpeningCrawl(4, "New description")).thenReturn(mockFilm.getEpisodeId());
        when(filmManagementPort.getFilmDetails(4)).thenReturn(mockFilm);
        when(filmMapper.toDetailsDto(mockFilm)).thenReturn(updatedDetailsDto);

        mockMvc.perform(put("/api/v1/films/4/description")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.openingCrawl").value("New description"));
    }
}
