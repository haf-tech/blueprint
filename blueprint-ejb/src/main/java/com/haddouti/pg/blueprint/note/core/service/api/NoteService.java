package com.haddouti.pg.blueprint.note.core.service.api;

import java.util.List;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.domain.NoteAttachment;

/**
 * Service interface for the bounded context <b>Note maintenance</b>.
 *
 */
public interface NoteService {

	/**
	 * Retrieves all the existing notes.
	 * 
	 * @return List of all notes
	 */
	public List<Note> retrieveAllNotes();

	/**
	 * Retrieves the note for the given ID.
	 * 
	 * @return Note
	 */
	public Note retrieveNote(Long id);

	/**
	 * Persist the given note.
	 * 
	 * @param note
	 *            New note object
	 * @return ID of the persisted note object
	 */
	public Long persistNote(Note note);

	/**
	 * Deletes the given note.
	 * 
	 * Deletes the given note, without a explicit check if the note exists.
	 * 
	 * @param note
	 *            Note object which should be deleted.
	 * @return TRUE if deletion was successful otherwise FALSE, for e.g. if the
	 *         given note object does not exists
	 */
	public Boolean deleteNote(Note note);

	/**
	 * Deletes note by the given ID.
	 * 
	 * Tries first to retrieve the note object to the given ID.
	 * 
	 * @param id
	 *            ID of an existing note
	 * @return TRUE if deletion was successful, otherwise FALSE, for e.g. if the
	 *         given note ID does not exists
	 */
	public Boolean deleteNoteById(Long id);

	/**
	 * Add the given attachment object to the existing note (ID).
	 * 
	 * @param noteId
	 *            ID of existing note object
	 * @param attachment
	 *            New attachment object
	 * @return ID of the persisted attachment
	 */
	public Long addAttachmentToNote(Long noteId, NoteAttachment attachment);

	/**
	 * Deletes the given attachment from the note.
	 * 
	 * Deletes the attachment, represented with the given attachment ID, from
	 * the note. The processing will fail, if the attachment or note does not
	 * exist.
	 * 
	 * @param noteId
	 *            Existing note ID
	 * @param attachmentId
	 *            Existing attachment ID
	 * @return TRUE if deletion of attachment was successful, otherwise FALSE
	 */
	public Boolean deleteAttachmentFromNote(Long noteId, Long attachmentId);
}
