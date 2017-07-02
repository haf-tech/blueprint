package com.haddouti.pg.bleuprint.note.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.haddouti.pg.blueprint.note.core.api.NoteRepository;
import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.domain.NoteAttachment;
import com.haddouti.pg.blueprint.note.infra.monitoring.NoteEventMonitoring;
import com.haddouti.pg.blueprint.note.jpa.JPAPersistenceConfig;

/**
 * Test unit for testing the (Spring Data) JPA repository implementation of
 * {@link NoteRepository}.
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { JPAPersistenceConfig.class })
public class NoteJPARepositoryTest {

	// here not necessary, best solution here to use "custom test slice spring
	// configuration"
	@MockBean
	private NoteEventMonitoring dummy;

	@Autowired
	@Qualifier("jpaNoteRepo")
	private NoteRepository repository;

	private DataHolder dh = new DataHolder();

	private static class DataHolder {
		private Note note1;
		private Note note2;
		private NoteAttachment attachment1;

		private List<Note> allNotes;
	}

	@Before
	public void up() {
		initData();

		// store the first note
		repository.save(dh.note1);
	}

	@Test
	public void testFindAll() {

		List<Note> allNotes = repository.findAll();
		Assert.assertNotNull(allNotes);
		Assert.assertTrue(allNotes.size() > 0);
	}

	@Test
	public void testFindById() {

		List<Note> allNotes = repository.findAll();
		Assert.assertNotNull(allNotes);
		Note oneNote = allNotes.get(0);

		Note note = repository.findById(oneNote.getId());
		Assert.assertNotNull(note);
		Assert.assertEquals(oneNote, note);
	}

	@Test
	public void testRemove() {

		Boolean ret = repository.remove(dh.note1);
		Assert.assertTrue(ret);
		Assert.assertNull(repository.findById(dh.note1.getId()));
	}

	@Test
	public void testSave() {
		Note note = new Note();
		note.setTitle("A new note: " + System.currentTimeMillis());
		note.setContent("With content");
		Long savedNotedId = repository.save(note);
		Assert.assertNotNull(savedNotedId);
		Assert.assertEquals(note.getTitle(), repository.findById(savedNotedId).getTitle());
	}

	private void initData() {

		dh.note1 = new Note();
		dh.note1.setId(1L);
		dh.note1.setTitle("Note#1");
		dh.note1.setContent("Note Content");
		dh.note1.setCreatedAt(new Date());
		dh.note1.setCreatedByUser("TestUnit");
		dh.note1.getTags().add("Test");
		dh.note1.getTags().add("TDD");
		dh.note1.getTags().add("Reactive");

		dh.note2 = new Note();
		dh.note2.setId(2L);
		dh.note2.setTitle("Note#2");
		dh.note2.setContent("Note Content");
		dh.note2.setCreatedAt(new Date());
		dh.note2.setCreatedByUser("TestUnit");
		dh.note2.getTags().add("Test");
		dh.note2.getTags().add("TDD");

		dh.attachment1 = new NoteAttachment();
		dh.attachment1.setId(20L);
		dh.attachment1.setContent("Attachment");
		dh.attachment1.setCreatedAt(new Date());

		dh.note1.getAttachments().add(dh.attachment1);

		dh.allNotes = new ArrayList<>();
		dh.allNotes.add(dh.note1);
		dh.allNotes.add(dh.note2);
	}

}
