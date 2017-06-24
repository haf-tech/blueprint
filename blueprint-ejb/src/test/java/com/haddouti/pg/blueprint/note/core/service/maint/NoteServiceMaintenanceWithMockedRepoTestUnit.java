package com.haddouti.pg.blueprint.note.core.service.maint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.haddouti.pg.blueprint.note.core.api.NoteEvent;
import com.haddouti.pg.blueprint.note.core.api.NoteRepository;
import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.domain.NoteAttachment;

/**
 * Test unit for testing {@link NoteServiceMaintenance}.
 * 
 * Test the {@link NoteServiceMaintenance} with a mocked repository. Any
 * modifications has no effect. For this reason is use case save always
 * positive.
 * 
 * Hint: Attachments are currently out of scope.
 */
public class NoteServiceMaintenanceWithMockedRepoTestUnit {

	@InjectMocks
	private NoteServiceMaintenance service = new NoteServiceMaintenance();

	// **** mocked services
	@Mock
	private NoteRepository mockRepo;

	// any action for this are ignored
	@Mock
	private NoteEvent mockEvent;

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
		MockitoAnnotations.initMocks(this);

		Mockito.when(mockRepo.findById(dh.note1.getId())).thenReturn(dh.note1);
		Mockito.when(mockRepo.findById(dh.note2.getId())).thenReturn(dh.note2);
		Mockito.when(mockRepo.findAll()).thenReturn(dh.allNotes);
		Mockito.when(mockRepo.remove(dh.note1)).thenReturn(Boolean.TRUE);
		// save is currently in this test unit out of scope, because we mocked
		// the repo
		Mockito.when(mockRepo.save(Mockito.any())).thenReturn(999L);
	}

	@Test
	public void testRetrieveAllNotes() {

		List<Note> allNotes = service.retrieveAllNotes();

		Assert.assertNotNull(allNotes);
		Assert.assertEquals(2, allNotes.size());
	}

	@Test
	public void testRetrieveOneNote() {
		Long id = 1L;
		Note aNote = service.retrieveNote(id);

		Assert.assertEquals(dh.note1, aNote);
	}

	@Test
	public void testDeleteExistingNote() {

		Assert.assertTrue(service.deleteNote(dh.note1));
	}

	@Test
	public void testDeleteUnavailableNote() {
		Note note = new Note();
		note.setId(999L);
		Assert.assertFalse(service.deleteNote(note));
	}

	@Test
	public void testDeleteNoteById() {

		Assert.assertTrue(service.deleteNoteById(dh.note1.getId()));
	}

	@Test
	public void testSaveNote() {
		Note note = new Note();
		note.setTitle("New Note");
		note.setContent("Content");

		Assert.assertNotNull(service.persistNote(note));
	}

	@Ignore
	public void testDeleteAttachmentFromNote() {
		Long noteId = 1L;
		Long attachmentId = 20L;
		service.deleteAttachmentFromNote(noteId, attachmentId);
	}

	@Ignore
	public void testAttachmentToNote() {

		Long noteId = 1L;
		NoteAttachment attachment = new NoteAttachment();
		service.addAttachmentToNote(noteId, attachment);
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
