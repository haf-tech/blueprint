package com.haddouti.pg.blueprint.web.rest;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.haddouti.pg.blueprint.web.Application;
import com.haddouti.pg.blueprint.web.SwaggerConfig;

/**
 * Generates Swagger Json.
 * 
 * Uses as test unit, to place this generation before Asciidoctor phase.
 *
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, SwaggerConfig.class })
@AutoConfigureMockMvc
public class RestNoteMaintenanceSwaggerJsonTest {

	// Call with mvn test, parameter is set through maven-surefire-plugin
	final String outputDir = System.getProperty("io.springfox.staticdocs.outputDir");

	@Inject
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void before() {
	}

	@Test
	public void generateSwaggerJson() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/v2/api-docs?group=Note").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		MockHttpServletResponse response = mvcResult.getResponse();
		String swaggerJson = response.getContentAsString();
		Files.createDirectories(Paths.get(outputDir));
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDir, "swagger.json"),
				StandardCharsets.UTF_8)) {
			writer.write(swaggerJson);
		}
	}
}
