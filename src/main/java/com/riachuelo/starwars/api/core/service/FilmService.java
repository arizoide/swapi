package com.riachuelo.starwars.api.core.service;

import com.riachuelo.starwars.api.core.model.Film;
import com.riachuelo.starwars.api.core.port.in.FilmManagementPort;
import com.riachuelo.starwars.api.core.port.out.FilmPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FilmService implements FilmManagementPort {

    private final FilmPersistancePort persistencePort;

    @Override
    public List<Film> listAllFilms() {
        return persistencePort.findAll();
    }

    @Override
    public Film getFilmDetails(int episodeId) {
        return persistencePort.findByEpisodeId(episodeId)
                .orElseThrow(() -> new NoSuchElementException("Filme com ID " + episodeId + " não encontrado."));
    }

    @Override
    @Transactional
    public Integer updateOpeningCrawl(int episodeId, String newOpeningCrawl) {
        // Executa o UPDATE SQL diretamente no banco de dados, sem verificação prévia. (Poderia ser feito
        // com um Get aqui, para fazer um fail fast, mas acho mais perfomtico update diretamente
        return persistencePort.updateOpeningCrawl(episodeId, newOpeningCrawl);
    }
}
