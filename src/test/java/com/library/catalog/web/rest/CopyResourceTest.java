package com.library.catalog.web.rest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.catalog.application.RegisterBookCopyUseCase;
import com.library.catalog.infrastructure.web.in.rest.CopyCommand;
import com.library.catalog.infrastructure.web.in.rest.CopyResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CopyResource.class)
class CopyResourceTest {

  @Autowired MockMvc mockMvc;

  @MockBean RegisterBookCopyUseCase registerBookCopyUseCase;

  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  void createCopy() throws Exception {
    var copyCommand = new CopyCommand("e45632f2-054a-4b2f-8dea-e56167779b9d", "ABC-abc-1234");
    var copyCommandValue = mapper.writeValueAsString(copyCommand);

    doNothing().when(registerBookCopyUseCase).execute(any(), any());

    mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/copies")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(copyCommandValue)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
  }

  @Test
  void createCopyBookIdConstraintViolation() throws Exception {
    var copyCommand = new CopyCommand("", "ABC-abc-1234");
    var copyCommandValue = mapper.writeValueAsString(copyCommand);

    mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/copies")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(copyCommandValue)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0]", containsString("Book id is required")));
  }

  @Test
  void createCopyBarcodeConstraintViolation() throws Exception {
    var copyCommand = new CopyCommand("e45632f2-054a-4b2f-8dea-e56167779b9d", "");
    var copyCommandValue = mapper.writeValueAsString(copyCommand);

    mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/copies")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(copyCommandValue)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$[0]", containsString("Barcode is required")));
  }
}
