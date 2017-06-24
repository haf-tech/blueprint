package com.haddouti.pg.blueprint.note.core.api;

/**
 * Interface for handling note specific events.
 * 
 * An implementation should handle this event logic in a asynchronous manner.
 *
 */
public interface NoteEvent {

	public static enum NoteActionType {
		ADD, UPDATE, DELETE
	}

	public void addNoteEvent(Long noteId, NoteActionType action);
}
