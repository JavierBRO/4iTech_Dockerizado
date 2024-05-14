package com.iTech.services;

import com.iTech.models.Keynote;

import java.util.List;


public interface KeynoteService {

    List<Keynote> findKeynoteVisibleTrue();

    Keynote findById(Long id);

    Keynote createKeynote(Keynote keynote);

    Keynote updateKeynote(Long id, Keynote keynote);

    boolean deleteKeynote(Long id);

}