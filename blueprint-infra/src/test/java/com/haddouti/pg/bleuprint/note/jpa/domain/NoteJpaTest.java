package com.haddouti.pg.bleuprint.note.jpa.domain;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.haddouti.pg.blueprint.note.jpa.domain.NoteJpa;

public class NoteJpaTest {

	@Test
	public void testEqualHashCode() {

		NoteJpa jpa1 = new NoteJpa();
		jpa1.setId(2L);
		jpa1.setContent("content");
		jpa1.setCreatedAt(new Date());
		jpa1.setCreatedByUser("user");
		jpa1.setTags(null);
		jpa1.setTitle("title");
		jpa1.setUpdatedAt(null);

		NoteJpa jpa2 = new NoteJpa();
		jpa2.setId(2L);
		jpa2.setContent("-----content-----");
		jpa2.setCreatedAt(null);
		jpa2.setCreatedByUser("---a---user---");
		jpa2.setTags(new ArrayList<>());
		jpa2.setTitle("title--and-so");
		jpa2.setUpdatedAt(new Date());

		Assert.assertTrue(jpa1.equals(jpa2) && jpa2.equals(jpa1));
		Assert.assertTrue(jpa1.hashCode() == jpa2.hashCode());
	}

	@Test
	public void testEqualHashCodeNegative() {

		NoteJpa jpa1 = new NoteJpa();
		jpa1.setId(2000L);
		jpa1.setContent("content");
		jpa1.setCreatedAt(new Date());
		jpa1.setCreatedByUser("user");
		jpa1.setTags(null);
		jpa1.setTitle("title");
		jpa1.setUpdatedAt(null);

		NoteJpa jpa2 = new NoteJpa();
		jpa2.setId(2L);
		jpa2.setContent("-----content-----");
		jpa2.setCreatedAt(null);
		jpa2.setCreatedByUser("---a---user---");
		jpa2.setTags(new ArrayList<>());
		jpa2.setTitle("title--and-so");
		jpa2.setUpdatedAt(new Date());

		Assert.assertFalse(jpa1.equals(jpa2) && jpa2.equals(jpa1));
		Assert.assertFalse(jpa1.hashCode() == jpa2.hashCode());
	}
}
