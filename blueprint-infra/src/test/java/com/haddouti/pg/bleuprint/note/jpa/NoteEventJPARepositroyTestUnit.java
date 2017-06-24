package com.haddouti.pg.bleuprint.note.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.haddouti.pg.blueprint.note.core.api.NoteEvent;
import com.haddouti.pg.blueprint.note.jpa.JPAPersistenceConfig;

/**
 * Test unit for testing the (Spring Data) JPA repository implementation of
 * {@link NoteEvent}.
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { JPAPersistenceConfig.class })
public class NoteEventJPARepositroyTestUnit {

	@Autowired
	@Qualifier("jpaNoteEventRepo")
	private NoteEvent event;

	@Test
	public void testAddEvent() {

		event.addNoteEvent(1L, NoteEvent.NoteActionType.ADD);
		try {
			// processing is async
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}

	@Test
	public void testAddEventUpdate() {

		event.addNoteEvent(1L, NoteEvent.NoteActionType.UPDATE);
		try {
			// processing is async
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
}
