package com.haddouti.pg.blueprint.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.service.api.NoteService;
import com.haddouti.pg.blueprint.web.rest.domain.NoteItem;
import com.haddouti.pg.blueprint.web.rest.domain.NoteRequest;
import com.haddouti.pg.blueprint.web.rest.domain.NoteResponse;
import com.haddouti.pg.blueprint.web.rest.domain.NoteResponse.StatusCode;

/**
 * REST interface for bounded context "note maintenance".
 *
 */
@RestController
@RequestMapping("/note/v1")
public class RestNoteMaintenance {

	@Inject
	private NoteService service;

	@RequestMapping(method = RequestMethod.GET, path = "/note")
	public NoteResponse getAllNotes() {

		List<NoteItem> noteItems = service.retrieveAllNotes().stream().map(MapUtil::toNoteItem)
				.collect(Collectors.toList());

		NoteResponse response = new NoteResponse();
		response.setItems(noteItems);

		StatusCode statusCode = new StatusCode();
		statusCode.setCode("0");
		statusCode.setText("OK");
		response.getResultStatus().add(statusCode);

		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/note", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody NoteResponse putNote(@RequestBody NoteRequest req) {

		final List<Long> noteIds = req.getItems().stream().map(MapUtil::toNote).map(service::persistNote)
				.collect(Collectors.toList());

		final List<Note> nodes = noteIds.stream().map(service::retrieveNote).collect(Collectors.toList());

		final List<NoteItem> noteItems = nodes.stream().map(MapUtil::toNoteItem).collect(Collectors.toList());

		NoteResponse response = new NoteResponse();
		response.setItems(noteItems);

		StatusCode statusCode = new StatusCode();
		statusCode.setCode("0");
		statusCode.setText("OK");
		response.getResultStatus().add(statusCode);
		statusCode = new StatusCode();
		statusCode.setCode("11");
		statusCode.setText("Saved.");
		response.getResultStatus().add(statusCode);

		return response;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/note", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody NoteResponse deleteNote(@RequestBody NoteRequest req) {

		List<Boolean> res = req.getItems().stream().map(MapUtil::toNote).map(n -> n.getId())
				.map(service::deleteNoteById).collect(Collectors.toList());

		NoteResponse response = new NoteResponse();
		StatusCode statusCode = new StatusCode();
		statusCode.setCode("0");
		statusCode.setText("OK");
		response.getResultStatus().add(statusCode);
		res.forEach(b -> {
			StatusCode sc = new StatusCode();
			if (b) {
				sc.setCode("10");
				sc.setText("Deleted.");
			} else {
				sc.setCode("50");
				sc.setText("Deletion failed.");
			}
			response.getResultStatus().add(sc);
		});

		return response;
	}
}
