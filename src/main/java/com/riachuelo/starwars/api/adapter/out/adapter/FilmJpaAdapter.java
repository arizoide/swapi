package com.riachuelo.starwars.api.adapter.out.adapter;

import com.riachuelo.starwars.api.adapter.out.persistence.FilmPersistenceMapper;
import com.riachuelo.starwars.api.adapter.out.persistence.FilmRepository;
import com.riachuelo.starwars.api.core.model.Film;
import com.riachuelo.starwars.api.core.port.out.FilmPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
@RequiredArgsConstructor
public class FilmJpaAdapter implements FilmPersistancePort {

    private final FilmRepository filmRepository;
    private final FilmPersistenceMapper mapper;

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Film> findByEpisodeId(int episodeId) {
        return filmRepository.findById(episodeId)
                .map(mapper::toDomain);
    }

    @Override
    @Transactional
    public Film save(Film film) {
        var entity = mapper.toEntity(film);
        var savedEntity = filmRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public Integer updateOpeningCrawl(int episodeId, String newOpeningCrawl) {
        return filmRepository.updateOpeningCrawl(episodeId, newOpeningCrawl);
    }
}
