package com.haddouti.pg.blueprint.web.rest;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.web.rest.domain.NoteItem;

public class MapUtil {

	public static NoteItem toNoteItem(Note note) {

		NoteItem ni = new NoteItem();
		ni.setTitle(note.getTitle());
		ni.setContent(note.getContent());
		return ni;
	}

	public static Note toNote(NoteItem noteItem) {

		Note ni = new Note();
		ni.setTitle(noteItem.getTitle());
		ni.setContent(noteItem.getContent());
		return ni;
	}
}
