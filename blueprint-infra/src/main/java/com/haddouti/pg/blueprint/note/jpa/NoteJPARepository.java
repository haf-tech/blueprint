package com.haddouti.pg.blueprint.note.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haddouti.pg.blueprint.note.core.api.NoteRepository;
import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.jpa.domain.NoteJpa;

/**
 * Implements {@link NoteRepository} with a Spring Data JPA realization.
 * 
 */
@Service("jpaNoteRepo")
public class NoteJPARepository implements NoteRepository {

	@Autowired
	private NoteJPADao dao;

	public Note findById(Long id) {

		NoteJpa jpaNote = dao.findOne(id);
		if (jpaNote != null) {
			return MapNoteUtil.toNote(jpaNote);
		}
		return null;
	}

	public List<Note> findAll() {

		List<NoteJpa> allJpaNotes = dao.findAll();
		List<Note> notes = allJpaNotes.stream().map(MapNoteUtil::toNote).collect(Collectors.toList());

		return notes;
	}

	@Transactional
	public Long save(Note note) {

		NoteJpa jpaNote = dao.save(MapNoteUtil.toJpaNote(note));

		return jpaNote.getId();
	}

	@Transactional
	public Boolean remove(Note note) {

		dao.delete(MapNoteUtil.toJpaNote(note));
		return Boolean.TRUE;
	}

}
