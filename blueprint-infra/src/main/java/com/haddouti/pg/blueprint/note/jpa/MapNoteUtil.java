package com.haddouti.pg.blueprint.note.jpa;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.jpa.domain.NoteJpa;

public class MapNoteUtil {

	private MapNoteUtil() {
	}

	public static Note toNote(NoteJpa jpa) {

		Note note = new Note();
		note.setId(jpa.getId());
		note.setAttachments(null);
		note.setContent(jpa.getContent());
		note.setCreatedAt(jpa.getCreatedAt());
		note.setCreatedByUser(jpa.getCreatedByUser());
		note.setTags(jpa.getTags());
		note.setTitle(jpa.getTitle());
		note.setUpdatedAt(jpa.getUpdatedAt());
		return note;
	}

	public static NoteJpa toJpaNote(Note note) {

		NoteJpa jpa = new NoteJpa();
		jpa.setId(note.getId());
		jpa.setContent(note.getContent());
		jpa.setCreatedAt(note.getCreatedAt());
		jpa.setCreatedByUser(note.getCreatedByUser());
		jpa.setTags(note.getTags());
		jpa.setTitle(note.getTitle());
		jpa.setUpdatedAt(note.getUpdatedAt());
		return jpa;
	}
}
