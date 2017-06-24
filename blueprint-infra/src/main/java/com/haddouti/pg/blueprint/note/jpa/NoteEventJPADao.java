package com.haddouti.pg.blueprint.note.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haddouti.pg.blueprint.note.jpa.domain.NoteEventJpa;

public interface NoteEventJPADao extends JpaRepository<NoteEventJpa, Long> {

}
