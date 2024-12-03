package com.example.Sistema.de.Gerenciamento;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {
    private static final Logger logger = LoggerFactory.getLogger(RecursoController.class);

    @Autowired
    private RecursoService recursoService;

    @GetMapping
    public List<Recurso> listarTodos() {
        logger.info("Listando todos os recursos");
        return recursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscarPorId(@PathVariable Long id) {
        logger.info("Buscando recurso com ID: {}", id);
        return recursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<Recurso> buscarPorTipo(@PathVariable String tipo) {
        logger.info("Buscando recursos do tipo: {}", tipo);
        return recursoService.buscarPorTipo(tipo);
    }

    @GetMapping("/paginado")
    public Page<Recurso> listarTodosPaginados(
            @RequestParam int page,
            @RequestParam int size) {
        logger.info("Listando recursos paginados: page={}, size={}", page, size);
        return recursoService.listarTodosPaginados(PageRequest.of(page, size));
    }

    @PostMapping
    public Recurso salvar(@RequestBody Recurso recurso) {
        logger.info("Salvando novo recurso: {}", recurso.getNome());
        return recursoService.salvar(recurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        logger.info("Excluindo recurso com ID: {}", id);
        if (recursoService.buscarPorId(id).isPresent()) {
            recursoService.excluir(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}