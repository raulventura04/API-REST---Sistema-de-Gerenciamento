package com.example.Sistema.de.Gerenciamento;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RecursoServiceTest {

    @Mock
    private RecursoRepository recursoRepository;

    @InjectMocks
    private RecursoService recursoService;

    public RecursoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorId() {
        Recurso recurso = new Recurso();
        recurso.setId(1L);
        recurso.setNome("Servidor");
        recurso.setTipo("Hardware");
        recurso.setDisponivel(true);

        when(recursoRepository.findById(1L)).thenReturn(Optional.of(recurso));

        Optional<Recurso> resultado = recursoService.buscarPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Servidor", resultado.get().getNome());
        verify(recursoRepository, times(1)).findById(1L);
    }
}