package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import com.itau.ms_desafio_itau.mapper.AutomovelMapper;
import com.itau.ms_desafio_itau.repository.AutomovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MsDesafioItauServiceImplTests {

//	@Mock
//	private AutomovelRepository repository;
//
//	@Mock
//	private AutomovelMapper mapper;
//
//	@InjectMocks
//	private AutomovelServiceImpl service;
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	void create_DeveSalvarAutomovelComSucesso() {
//		AutomovelRequestDTO requestDTO = new AutomovelRequestDTO("Fiat", "bolinha", BigDecimal.valueOf(12200L));
//		Automovel automovel = new Automovel();
//
//		when(mapper.toEntity(requestDTO)).thenReturn(automovel);
//		when(repository.save(automovel)).thenReturn(automovel);
//
//		assertDoesNotThrow(() -> service.create(requestDTO));
//		verify(repository, times(1)).save(automovel);
//	}
//
//	@Test
//	void create_DeveLancarExcecaoQuandoErroAoSalvar() {
//		AutomovelRequestDTO requestDTO = new AutomovelRequestDTO("Toyota", "Toyota plus", BigDecimal.valueOf(12000L));
//		Automovel automovel = new Automovel();
//
//		when(mapper.toEntity(requestDTO)).thenReturn(automovel);
//		when(repository.save(automovel)).thenThrow(new RuntimeException("Erro interno"));
//
//		IllegalStateException ex = assertThrows(IllegalStateException.class, () -> service.create(requestDTO));
//		assertTrue(ex.getMessage().contains("Erro ao salvar o automóvel"));
//	}
//
//	@Test
//	void listaAll_DeveRetornarListaDeAutomoveis() {
//		Automovel automovel = new Automovel();
//		List<Automovel> automoveis = List.of(automovel);
//		List<AutomovelResponseDTO> responseDTOs = List.of(new AutomovelResponseDTO( 1L, "Toyota", "Toyota plus", BigDecimal.valueOf(12000L), LocalDateTime.now()));
//
//		when(repository.findAll()).thenReturn(automoveis);
//		when(mapper.toResponseListDTO(automoveis)).thenReturn(responseDTOs);
//
//		List<AutomovelResponseDTO> resultado = service.listaAll();
//		assertEquals(1, resultado.size());
//	}
//
//	@Test
//	void listaAll_DeveLancarExcecaoQuandoListaVazia() {
//		when(repository.findAll()).thenReturn(Collections.emptyList());
//
//		EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.listaAll());
//		assertEquals("Nenhum automóvel encontrado", ex.getMessage());
//	}
//
//	@Test
//	void findById_DeveRetornarAutomovelQuandoEncontrado() {
//		Long id = 2L;
//		Automovel automovel = new Automovel();
//		AutomovelResponseDTO responseDTO = new AutomovelResponseDTO(
//				2L,
//				"Toyota",
//				"Toyota plus",
//				BigDecimal.valueOf(12000L),
//				LocalDateTime.now()
//		);
//
//		when(repository.findById(id)).thenReturn(Optional.of(automovel));
//		when(mapper.toResponseDTO(automovel)).thenReturn(responseDTO);
//
//		AutomovelResponseDTO resultado = service.findById(id);
//		assertEquals(responseDTO, resultado);
//	}
//
//	@Test
//	void findById_DeveLancarExcecaoQuandoNaoEncontrado() {
//		Long id = 1L;
//		when(repository.findById(id)).thenReturn(Optional.empty());
//
//		EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> service.findById(id));
//		assertEquals("Automóvel não encontrado com id: 1", ex.getMessage());
//	}

}