package com.itau.ms_desafio_itau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.service.IAutomovelService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AutomovelControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private IAutomovelService automovelService;

	@Test
	void createAutomovel_ShouldReturnCreatedStatus() throws Exception {
		AutomovelRequestDTO requestDTO = new AutomovelRequestDTO(
				"Fiat", "ABC-1234", BigDecimal.valueOf(120.000));

		mockMvc.perform(post("/api")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDTO)))
				.andExpect(status().isCreated());

		Mockito.verify(automovelService).create(requestDTO);
	}

	@Test
	void createAutomovel_WithInvalidData_ShouldReturnBadRequest() throws Exception {
		AutomovelRequestDTO invalidRequestDTO = new AutomovelRequestDTO(
				"", "", null);

		mockMvc.perform(post("/api")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidRequestDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void listAllAutomoveis_ShouldReturnList() throws Exception {
		LocalDateTime date = LocalDateTime.now();
		AutomovelResponseDTO responseDTO1 = new AutomovelResponseDTO(1L, "Fiat", "ABC-1234", BigDecimal.valueOf(120.000), date);
		AutomovelResponseDTO responseDTO2 = new AutomovelResponseDTO(2L, "Volkswagen", "DEF-5678", BigDecimal.valueOf(120.000), date);
		List<AutomovelResponseDTO> automoveis = Arrays.asList(responseDTO1, responseDTO2);

		Mockito.when(automovelService.listaAll()).thenReturn(automoveis);

		mockMvc.perform(get("/api"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].nome").value("ABC-1234"))
				.andExpect(jsonPath("$[1].nome").value("DEF-5678"));
	}

	@Test
	void listAllAutomoveis_WhenEmpty_ShouldReturnOkWithEmptyList() throws Exception {
		Mockito.when(automovelService.listaAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(0));
	}

	@Test
	void searchById_ShouldReturnAutomovel() throws Exception {
		Long id = 1L;
		LocalDateTime date = LocalDateTime.now();
		AutomovelResponseDTO responseDTO = new AutomovelResponseDTO(id, "Fiat", "ABC-1234", BigDecimal.valueOf(120.000), date);

		Mockito.when(automovelService.searchById(id)).thenReturn(responseDTO);

		mockMvc.perform(get("/api/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.nome").value("ABC-1234"));
	}

	@Test
	void searchById_WhenNotFound_ShouldReturnNotFound() throws Exception {
		Long id = 99L;
		Mockito.when(automovelService.searchById(id))
				.thenThrow(new EntityNotFoundException("Automóvel não encontrado com id: " + id));

		mockMvc.perform(get("/api/{id}", id))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.error").value("Recurso não encontrado"))
				.andExpect(jsonPath("$.message").value("Automóvel não encontrado com id: " + id));
	}


	@Test
	void updateAutomovel_ShouldReturnNoContent() throws Exception {
		Long id = 1L;
		AutomovelRequestDTO requestDTO = new AutomovelRequestDTO(
				"Fiat", "ABC-1234", BigDecimal.valueOf(120.000));

		mockMvc.perform(put("/api/{id}", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDTO)))
				.andExpect(status().isNoContent());

		Mockito.verify(automovelService).update(id, requestDTO);
	}

	@Test
	void updateAutomovel_WithInvalidData_ShouldReturnBadRequest() throws Exception {
		Long id = 1L;
		AutomovelRequestDTO invalidRequestDTO = new AutomovelRequestDTO(
				"", "", null);

		mockMvc.perform(put("/api/{id}", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidRequestDTO)))
				.andExpect(status().isBadRequest());
	}

	@Test
	void deleteAutomovel_ShouldReturnNoContent() throws Exception {
		Long id = 1L;

		mockMvc.perform(delete("/api/{id}", id))
				.andExpect(status().isNoContent());

		Mockito.verify(automovelService).delete(id);
	}
}