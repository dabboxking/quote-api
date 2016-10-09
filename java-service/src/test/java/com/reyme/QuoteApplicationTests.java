package com.reyme;

import com.reyme.quote.QuoteRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteRestController.class)
@AutoConfigureRestDocs("target/generated-snippets")
public class QuoteApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void readQuotes() throws Exception {
        this.mvc.perform(get("/quotes").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("read-quotes"));
    }
}