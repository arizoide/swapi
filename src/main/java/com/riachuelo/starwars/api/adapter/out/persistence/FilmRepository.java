package com.riachuelo.starwars.api.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    @Modifying
    @Query("UPDATE FilmEntity f SET f.openingCrawl = :newOpeningCrawl, f.version = f.version + 1 WHERE f.episodeId = :episodeId")
    Integer updateOpeningCrawl(@Param("episodeId") int episodeId, @Param("newOpeningCrawl") String newOpeningCrawl);
}
