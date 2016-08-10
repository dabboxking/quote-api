package com.reyme;

import com.reyme.author.Author;
import com.reyme.author.AuthorRepository;
import com.reyme.quote.Quote;
import com.reyme.quote.QuoteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class QuoteApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
				hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private Quote quote;
	private Author author;

	@Rule
	public JUnitRestDocumentation restDocumentation =
			new JUnitRestDocumentation("target/generated-snippets");

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        //this.mockMvc = webAppContextSetup(this.context).build();
		this.quoteRepository.deleteAllInBatch();
		this.authorRepository.deleteAllInBatch();
		this.author = authorRepository.save(new Author("Bill","Gates"));
		this.quote = quoteRepository.save(new Quote(author, "I make money!"));
	}

	@Test
	public void testAddAuthor() throws  Exception {
		String authorJson = json(author);
		mockMvc.perform(post("/authors")
			.contentType(contentType)
			.content(authorJson))
			.andExpect(status().isCreated())
            .andDo(document("authors"));
	}

	@Test
	public void testReadAuthors() throws Exception {
		mockMvc.perform(get("/authors"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(author.getId().intValue())))
				.andExpect(jsonPath("$[0].fullName", is("Bill Gates")));
	}

	@Test
	public void testAddQuote() throws Exception{
		String quoteJson = json(quote);
		mockMvc.perform(post("/quotes")
				.contentType(contentType)
				.content(quoteJson))
				.andExpect(status().isCreated());
	}

	@Test
	public void testReadQuotes() throws Exception {
		this.mockMvc.perform(get("/quotes"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(quote.getId().intValue())))
				.andExpect(jsonPath("$[0].name", is("Bill Gates")))
				.andExpect(jsonPath("$[0].content", is("I make money!")));
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
