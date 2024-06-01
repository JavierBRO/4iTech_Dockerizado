package com.iTech.repository;

import com.iTech.models.Track;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByVisibleTrue();

    List<Track> findAllByVisibleTrue();


}