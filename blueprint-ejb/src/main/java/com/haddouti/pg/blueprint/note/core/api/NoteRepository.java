package com.haddouti.pg.blueprint.note.core.api;

import java.util.List;

import com.haddouti.pg.blueprint.note.core.domain.Note;

/**
 * Repository interface for {@link Note} instances.
 *
 * Interface declares "query methods" to work with single entities or collection
 * of them.
 */
public interface NoteRepository {

	/**
	 * Finds note by given ID.
	 * 
	 * @param id
	 *            Note ID
	 * @return Note object or null
	 */
	public Note findById(Long id);

	/**
	 * Finds all notes without any restriction.
	 * 
	 * @return List of notes or empty list.
	 */
	public List<Note> findAll();

	/**
	 * Saves the given note.
	 * 
	 * @param note
	 *            Note object
	 * @return The new ID
	 */
	public Long save(Note note);

	/**
	 * Removes the given note.
	 * 
	 * @param note
	 *            Note, which will be deleted
	 * @return TRUE if successful, otherwise FALSE
	 */
	public Boolean remove(Note note);

}
