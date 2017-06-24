package com.haddouti.pg.blueprint.note.jpa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haddouti.pg.blueprint.note.core.api.NoteEvent;
import com.haddouti.pg.blueprint.note.jpa.domain.NoteEventJpa;

/**
 * Repository implementation of {@link NoteEvent} with Spring Data JPA.
 * 
 * The implementation is asynchronous.
 *
 */
@Service("jpaNoteEventRepo")
public class NoteEventJPARepository implements NoteEvent {

	@Autowired
	private NoteEventJPADao dao;

	@Override
	@Async
	@Transactional
	public void addNoteEvent(Long id, NoteActionType action) {

		NoteEventJpa event = new NoteEventJpa();
		event.setCreatedAt(new Date());
		event.setAction(action.name());

		dao.save(event);
	}

}
