package com.riachuelo.starwars.api.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "films")
@Getter
@Setter
public class FilmEntity {

    @Id
    private int episodeId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(length = 1024)
    private String openingCrawl;

    private String director;
    private String producer;
    private LocalDate releaseDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_characters", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "character_url")
    private List<String> characters;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_planets", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "planet_url")
    private List<String> planets;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_starships", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "starship_url")
    private List<String> starships;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_vehicles", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "vehicle_url")
    private List<String> vehicles;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_species", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "species_url")
    private List<String> species;

    private String url;
    private String created;
    private String edited;

    @Version
    private int version;
}
