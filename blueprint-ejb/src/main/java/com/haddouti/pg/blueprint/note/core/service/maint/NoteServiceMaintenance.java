package com.haddouti.pg.blueprint.note.core.service.maint;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.haddouti.pg.blueprint.note.core.api.NoteEvent;
import com.haddouti.pg.blueprint.note.core.api.NoteEvent.NoteActionType;
import com.haddouti.pg.blueprint.note.core.api.NoteRepository;
import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.domain.NoteAttachment;
import com.haddouti.pg.blueprint.note.core.service.api.NoteService;

/**
 * Service implementation of {@link NoteService}.
 * 
 * This implementation uses a {@link NoteRepository} for the integration to a
 * possible repository lib. A direct relationship to a concrete realization does
 * not exist (Dependency Inversion Principle).
 *
 */
@Named("noteService")
public class NoteServiceMaintenance implements NoteService {

	@Inject
	private NoteRepository noteRepo;

	@Inject
	private NoteEvent noteEvent;

	@Override
	public List<Note> retrieveAllNotes() {

		return noteRepo.findAll();
	}

	@Override
	public Note retrieveNote(Long id) {

		return noteRepo.findById(id);
	}

	@Override
	public Long persistNote(Note note) {

		NoteActionType actionType;
		// update the timestamps
		if (note.getId() != null) {
			note.setUpdatedAt(new Date());
			actionType = NoteActionType.UPDATE;
		} else {
			note.setCreatedAt(new Date());
			actionType = NoteActionType.ADD;
		}

		Long newId = noteRepo.save(note);
		noteEvent.addNoteEvent(newId, actionType);
		return newId;
	}

	@Override
	public Boolean deleteNote(Note note) {

		return noteRepo.remove(note);
	}

	@Override
	public Boolean deleteNoteById(Long id) {

		noteEvent.addNoteEvent(id, NoteActionType.DELETE);
		Note existingNote = retrieveNote(id);
		if (existingNote == null) {
			return Boolean.FALSE;
		} else {
			return deleteNote(existingNote);
		}
	}

	@Override
	public Long addAttachmentToNote(Long noteId, NoteAttachment attachment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAttachmentFromNote(Long noteId, Long attachmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
