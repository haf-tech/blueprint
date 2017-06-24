package com.haddouti.pg.blueprint.note.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haddouti.pg.blueprint.note.jpa.domain.NoteJpa;

public interface NoteJPADao extends JpaRepository<NoteJpa, Long> {

}
