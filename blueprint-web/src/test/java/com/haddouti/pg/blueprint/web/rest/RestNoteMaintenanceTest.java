package com.haddouti.pg.blueprint.web.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.haddouti.pg.blueprint.note.core.domain.Note;
import com.haddouti.pg.blueprint.note.core.service.api.NoteService;
import com.haddouti.pg.blueprint.web.Application;
import com.haddouti.pg.blueprint.web.rest.domain.NoteItem;
import com.haddouti.pg.blueprint.web.rest.domain.NoteRequest;
import com.haddouti.pg.blueprint.web.rest.domain.NoteResponse;

/**
 * Test unit for the REST interface and bounded context "note maintenance".
 *
 */
@RunWith(SpringRunner.class)
// use own existing application configuration
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RestNoteMaintenanceTest {

	private MediaType contentTypeJson = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MediaType contentTypeXml = new MediaType(MediaType.APPLICATION_XML.getType(),
			MediaType.APPLICATION_XML.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Inject
	private NoteService service;

	// Data
	private Note note = new Note();

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void up() {

		// MockMvs and WebApplicationContext is the base for all test cases
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// prepare data
		service.deleteNoteById(1L);
		note.setTitle("Title 1");
		note.setContent("Content 1");
		service.persistNote(note);
	}

	@Test
	public void testGetAllNotes() throws Exception {

		mockMvc.perform(get("/note/v1/note")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items[0].title", is(note.getTitle())));
	}

	@Test
	public void testPutNoteXml() throws Exception {

		final NoteRequest req = new NoteRequest();
		NoteItem noteItem = new NoteItem();
		noteItem.setTitle("New Title 2");
		noteItem.setContent("New Content 2");
		req.getItems().add(noteItem);

		final String xml = xml(NoteRequest.class, req);

		mockMvc.perform(put("/note/v1/note").content(xml).contentType(contentTypeXml)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items", hasSize(1)));
	}

	@Test
	public void testPutNoteJson() throws Exception {

		final NoteRequest req = new NoteRequest();
		NoteItem noteItem = new NoteItem();
		noteItem.setTitle("New Title 3");
		noteItem.setContent("New Content 3");
		req.getItems().add(noteItem);

		final String json = json(req);

		mockMvc.perform(put("/note/v1/note").content(json).contentType(contentTypeJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items", hasSize(1)));
	}

	@Test
	@Transactional
	public void testDeleteNoteJson() throws Exception {

		final NoteRequest req = new NoteRequest();
		NoteItem noteItem = new NoteItem();
		noteItem.setId(4L);
		noteItem.setTitle("New Title 3");
		noteItem.setContent("New Content 3");
		req.getItems().add(noteItem);

		String json = json(req);
		// add a new one to the storage
		mockMvc.perform(put("/note/v1/note").content(json).contentType(contentTypeJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items", hasSize(1)));

		// retrieve the first ID, which will be deleted
		final NoteResponse noteResponse = fromJson(
				mockMvc.perform(get("/note/v1/note")).andReturn().getResponse().getContentAsString());
		final int sizeAfterAdd = noteResponse.getItems().size();
		final Long idToRemove = noteResponse.getItems().get(0).getId();
		noteItem.setId(idToRemove);

		json = json(req);

		// // retrieve
		// mockMvc.perform(get("/note/v1/note")).andExpect(status().isOk()).andDo(print())
		// .andExpect(content().contentType(contentTypeJson))
		// .andExpect(jsonPath("$.items", hasSize(baseSize + 1)));

		// delete
		mockMvc.perform(delete("/note/v1/note").content(json).contentType(contentTypeJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items", hasSize(0)));

		// retrieve
		mockMvc.perform(get("/note/v1/note")).andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.items", hasSize(sizeAfterAdd - 1)));
	}

	@Test
	// @Transactional
	public void testDeleteNoteNegativeJson() throws Exception {

		final NoteRequest req = new NoteRequest();
		NoteItem noteItem = new NoteItem();
		noteItem.setId(4099900L);
		noteItem.setTitle("New Title 3");
		noteItem.setContent("New Content 3");
		req.getItems().add(noteItem);

		final String json = json(req);

		// delete
		mockMvc.perform(delete("/note/v1/note").content(json).contentType(contentTypeJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(contentTypeJson))
				.andExpect(jsonPath("$.resultStatus[0].code", is("0")))
				.andExpect(jsonPath("$.resultStatus[1].code", is("50")));

	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	private NoteResponse fromJson(String content) throws IOException {
		MockHttpInputMessage msg = new MockHttpInputMessage(content.getBytes());
		return (NoteResponse) this.mappingJackson2HttpMessageConverter.read(NoteResponse.class, msg);
	}

	private String xml(Class clazz, Object o) throws Exception {
		final JAXBContext jbc = JAXBContext.newInstance(clazz);

		Marshaller m = jbc.createMarshaller();
		StringWriter s = new StringWriter();
		m.marshal(o, s);
		System.out.println(s.toString());
		return s.toString();
	}
}
