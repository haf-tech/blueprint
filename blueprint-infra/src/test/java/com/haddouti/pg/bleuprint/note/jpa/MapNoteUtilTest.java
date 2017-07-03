package com.haddouti.pg.bleuprint.note.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.jpa.MapNoteUtil;
import com.haddouti.pg.blueprint.note.jpa.domain.NoteJpa;

public class MapNoteUtilTest {

	@Test
	public void testToNote() {
		final NoteJpa jpa = new NoteJpa();
		jpa.setContent("content");
		jpa.setCreatedAt(new Date());
		jpa.setCreatedByUser("user");
		jpa.setId(2L);
		List<String> tags = new ArrayList<>();
		tags.add("tag1");
		jpa.setTags(tags);
		jpa.setTitle("title");
		jpa.setUpdatedAt(new Date());
		final Note note = MapNoteUtil.toNote(jpa);

		Assert.assertEquals(jpa.getContent(), note.getContent());
		Assert.assertEquals(jpa.getCreatedByUser(), note.getCreatedByUser());
		Assert.assertEquals(jpa.getCreatedAt(), note.getCreatedAt());
		Assert.assertEquals(jpa.getId(), note.getId());
		Assert.assertEquals(jpa.getTags(), note.getTags());
		Assert.assertEquals(jpa.getTitle(), note.getTitle());
		Assert.assertEquals(jpa.getUpdatedAt(), note.getUpdatedAt());

	}

	@Test
	public void testToJpaNote() {
		final Note note = new Note();
		note.setContent("content");
		note.setCreatedAt(new Date());
		note.setCreatedByUser("user");
		note.setId(2L);
		List<String> tags = new ArrayList<>();
		tags.add("tag1");
		note.setTags(tags);
		note.setTitle("title");
		note.setUpdatedAt(new Date());
		final NoteJpa jpa = MapNoteUtil.toJpaNote(note);

		Assert.assertEquals(note.getContent(), jpa.getContent());
		Assert.assertEquals(note.getCreatedByUser(), jpa.getCreatedByUser());
		Assert.assertEquals(note.getCreatedAt(), jpa.getCreatedAt());
		Assert.assertEquals(note.getId(), jpa.getId());
		Assert.assertEquals(note.getTags(), jpa.getTags());
		Assert.assertEquals(note.getTitle(), jpa.getTitle());
		Assert.assertEquals(note.getUpdatedAt(), jpa.getUpdatedAt());
		Assert.assertNotEquals("h", jpa.getTitle());

	}
}
