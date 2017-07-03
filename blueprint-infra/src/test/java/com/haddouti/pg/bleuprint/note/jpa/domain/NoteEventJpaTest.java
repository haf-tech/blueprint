package com.haddouti.pg.bleuprint.note.jpa.domain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.haddouti.pg.blueprint.note.jpa.domain.NoteEventJpa;

public class NoteEventJpaTest {

	@Test
	public void testEqualHashCode() {

		NoteEventJpa jpa1 = new NoteEventJpa();
		jpa1.setId(2L);
		jpa1.setAction("A");
		jpa1.setCreatedAt(null);
		jpa1.setNoteId(20L);

		NoteEventJpa jpa2 = new NoteEventJpa();
		jpa2.setId(2L);
		jpa2.setAction("B");
		jpa2.setCreatedAt(new Date());
		jpa2.setNoteId(99990L);

		Assert.assertTrue(jpa1.equals(jpa2) && jpa2.equals(jpa1));
		Assert.assertTrue(jpa1.hashCode() == jpa2.hashCode());
	}

	@Test
	public void testEqualHashCodeNegative() {

		NoteEventJpa jpa1 = new NoteEventJpa();
		jpa1.setId(2L);
		jpa1.setAction("A");
		jpa1.setCreatedAt(null);
		jpa1.setNoteId(20L);

		NoteEventJpa jpa2 = new NoteEventJpa();
		jpa2.setId(987654L);
		jpa2.setAction("B");
		jpa2.setCreatedAt(new Date());
		jpa2.setNoteId(99990L);

		Assert.assertFalse(jpa1.equals(jpa2) && jpa2.equals(jpa1));
		Assert.assertFalse(jpa1.hashCode() == jpa2.hashCode());
	}
}
