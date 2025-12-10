package com.riachuelo.starwars.api.adapter.out.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FilmRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FilmRepository filmRepository;

    @Test
    @DisplayName("Deve atualizar o openingCrawl e incrementar a versão via SQL nativo")
    void deveAtualizarOpeningCrawlEIncrementarVersao() {
        FilmEntity entity = new FilmEntity();
        entity.setEpisodeId(4);
        entity.setTitle("A New Hope");
        entity.setOpeningCrawl("Old Crawl");
        entity.setVersion(1);
        entity.setReleaseDate(LocalDate.now());
        entity.setDirector("Lucas");
        entity.setProducer("Kurtz");
        
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.clear();

        filmRepository.updateOpeningCrawl(4, "New Crawl");
        
        entityManager.clear();

        Optional<FilmEntity> updatedFilm = filmRepository.findById(4);

        assertThat(updatedFilm).isPresent();
        assertThat(updatedFilm.get().getOpeningCrawl()).isEqualTo("New Crawl");
        assertThat(updatedFilm.get().getVersion()).isEqualTo(2);
    }
}
