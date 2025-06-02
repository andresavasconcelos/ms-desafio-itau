package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import com.itau.ms_desafio_itau.mapper.AutomovelMapper;
import com.itau.ms_desafio_itau.repository.AutomovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MsDesafioItauServiceTests {

	@Mock
	private AutomovelRepository repository;

	@Mock
	private AutomovelMapper mapper;

	@InjectMocks
	private AutomovelServiceImpl service;

	@Test
	void deveCriarAutomovelComSucesso() {
		AutomovelRequestDTO request = new AutomovelRequestDTO("Honda", "Civic Touring", new BigDecimal(105000.0));
		Automovel automovel = new Automovel(1L, "Honda", "Civic Touring", new BigDecimal(105000.0), LocalDateTime.now());

		when(mapper.toEntity(request)).thenReturn(automovel);

		assertDoesNotThrow(() -> service.create(request));
		verify(repository).save(automovel);
	}

	@Test
	void deveLancarExcecaoAoCriarAutomovel() {
		AutomovelRequestDTO request = new AutomovelRequestDTO("Honda", "Civic", new BigDecimal(95000.0));
		when(mapper.toEntity(any())).thenThrow(new RuntimeException("Erro mapeamento"));

		IllegalStateException ex = assertThrows(IllegalStateException.class, () -> service.create(request));
		assertTrue(ex.getMessage().contains("Erro ao salvar o automóvel"));
	}

	@Test
	void deveRetornarListaDeAutomoveis() {
		LocalDateTime date = LocalDateTime.now();
		Automovel auto1 = new Automovel(1L, "Honda", "Civic", new BigDecimal(100000.0), date);

		List<Automovel> automoveis = List.of(auto1);

		AutomovelResponseDTO autoresp = new AutomovelResponseDTO(1L, "Honda", "Civic", new BigDecimal(100000.0), date);
		List<AutomovelResponseDTO> dtos = List.of(autoresp);

		when(repository.findAll()).thenReturn(automoveis);
		when(mapper.toResponseListDTO(automoveis)).thenReturn(dtos);

		List<AutomovelResponseDTO> resultado = service.listaAll();

		assertEquals(1, resultado.size());
		assertEquals("Civic", resultado.get(0).nome());
		verify(repository).findAll();
		verify(mapper).toResponseListDTO(automoveis);
	}

	@Test
	void deveLancarExcecaoQuandoListaEstiverVazia() {
		when(repository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(EntityNotFoundException.class, () -> service.listaAll());
	}

	@Test
	void deveBuscarAutomovelPorIdComSucesso() {
		Long id = 1L;
		LocalDateTime date = LocalDateTime.now();

		Automovel automovel = new Automovel(1L, "Honda", "Civic", new BigDecimal(95000.0), date);
		AutomovelResponseDTO dto = new AutomovelResponseDTO(1L, "Honda", "Civic", new BigDecimal(95000.0), date);

		when(repository.findById(id)).thenReturn(Optional.of(automovel));
		when(mapper.toResponseDTO(automovel)).thenReturn(dto);

		AutomovelResponseDTO result = service.searchById(id);

		assertEquals(dto, result);
	}

	@Test
	void deveLancarExcecaoQuandoBuscarPorIdInexistente() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> service.searchById(99L));
	}

	@Test
	void deveAtualizarAutomovel() {
		Long id = 1L;
		AutomovelRequestDTO dto = new AutomovelRequestDTO("Toyota", "Corolla", new BigDecimal(120000.0));

		Automovel existente = new Automovel(id, "Toyota", "Corolla", new BigDecimal(100000.0), null);

		Automovel mapeado = new Automovel();
		mapeado.setMarca("Toyota");
		mapeado.setNome("Corolla");
		mapeado.setPreco(new BigDecimal(120000.0));

		when(repository.findById(id)).thenReturn(Optional.of(existente));
		when(mapper.toEntity(dto)).thenReturn(mapeado);

		service.update(id, dto);

		ArgumentCaptor<Automovel> captor = ArgumentCaptor.forClass(Automovel.class);
		verify(repository).save(captor.capture());

		Automovel salvo = captor.getValue();
		assertEquals(id, salvo.getId());
		assertEquals("Toyota", salvo.getMarca());
		assertEquals("Corolla", salvo.getNome());
		assertEquals(new BigDecimal(120000.0), salvo.getPreco());
	}

	@Test
	void deveLancarExcecaoAoAtualizarComIdInexistente() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		AutomovelRequestDTO dto = new AutomovelRequestDTO("Toyota", "Yaris", new BigDecimal(80000.0));

		assertThrows(EntityNotFoundException.class, () -> service.update(123L, dto));
	}

	@Test
	void deveDeletarAutomovelComSucesso() {
		LocalDateTime date = LocalDateTime.now();
		Automovel automovel = new Automovel();
		automovel.setId(1L);
		automovel.setMarca("Honda");
		automovel.setNome("Civic");
		automovel.setPreco(new BigDecimal(95000.0));
		automovel.setDate(date);

		when(repository.findById(1L)).thenReturn(Optional.of(automovel));

		service.delete(1L);

		verify(repository).deleteById(1L);
	}

	@Test
	void deveLancarExcecaoAoDeletarAutomovelInexistente() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> service.delete(999L));
	}
}