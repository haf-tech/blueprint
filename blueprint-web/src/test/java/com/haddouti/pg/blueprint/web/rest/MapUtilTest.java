package com.haddouti.pg.blueprint.web.rest;

import org.junit.Assert;
import org.junit.Test;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.web.rest.domain.NoteItem;

public class MapUtilTest {

	@Test
	public void testToNote() {

		NoteItem noteItem = new NoteItem();
		noteItem.setContent("content");
		noteItem.setId(3L);
		noteItem.setTitle("title");
		noteItem.setUserId("user");

		final Note note = MapUtil.toNote(noteItem);

		Assert.assertEquals(noteItem.getContent(), note.getContent());
		Assert.assertEquals(noteItem.getTitle(), note.getTitle());
		Assert.assertEquals(noteItem.getUserId(), note.getCreatedByUser());
		Assert.assertEquals(noteItem.getId(), note.getId());
		Assert.assertEquals(0, note.getTags().size());
	}

	@Test
	public void testToNoteItem() {

		Note note = new Note();
		note.setContent("content");
		note.setId(3L);
		note.setTitle("title");
		note.setCreatedByUser("user");

		final NoteItem noteItem = MapUtil.toNoteItem(note);

		Assert.assertEquals(note.getContent(), noteItem.getContent());
		Assert.assertEquals(note.getTitle(), noteItem.getTitle());
		Assert.assertEquals(note.getCreatedByUser(), noteItem.getUserId());
		Assert.assertEquals(note.getId(), noteItem.getId());
	}
}
