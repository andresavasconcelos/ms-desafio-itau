package com.itau.ms_desafio_itau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.service.IAutomovelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutomovelController.class)
class MsDesafioItauControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private IAutomovelService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void deveCriarAutomovelComSucesso() throws Exception {
		AutomovelRequestDTO request = new AutomovelRequestDTO("Honda", "Civic", new BigDecimal(105000.0));

		mockMvc.perform(post("/automoveis")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated());
	}

	@Test
	void deveListarAutomoveis() throws Exception {
		List<AutomovelResponseDTO> lista = List.of(
				new AutomovelResponseDTO(1L, "Toyota", "Corolla", new BigDecimal(120000.0), LocalDateTime.now())
		);

		Mockito.when(service.listaAll()).thenReturn(lista);

		mockMvc.perform(get("/automoveis"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nome").value("Corolla"));
	}

	@Test
	void deveBuscarAutomovelPorId() throws Exception {
		Long id = 1L;
		AutomovelResponseDTO dto = new AutomovelResponseDTO(id, "Hyundai", "HB20", new BigDecimal(80000.0), LocalDateTime.now());

		Mockito.when(service.searchById(id)).thenReturn(dto);

		mockMvc.perform(get("/automoveis/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.marca").value("Hyundai"));
	}

	@Test
	void deveAtualizarAutomovelComSucesso() throws Exception {
		AutomovelRequestDTO request = new AutomovelRequestDTO("Hyundai", "HB20S", new BigDecimal(85000.0));

		mockMvc.perform(put("/automoveis/{id}", 1L)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isNoContent());
	}

	@Test
	void deveDeletarAutomovelComSucesso() throws Exception {
		mockMvc.perform(delete("/automoveis/{id}", 1L))
				.andExpect(status().isNoContent());
	}
}