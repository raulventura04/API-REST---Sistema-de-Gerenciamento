package com.example.Sistema.de.Gerenciamento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecursoController.class)
class RecursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecursoService recursoService;

    @Test
    void testListarTodos() throws Exception {
        Recurso recurso = new Recurso();
        recurso.setId(1L);
        recurso.setNome("Servidor");
        recurso.setTipo("Hardware");
        recurso.setDisponivel(true);

        when(recursoService.listarTodos()).thenReturn(Collections.singletonList(recurso));

        mockMvc.perform(get("/api/recursos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Servidor"));
    }
}