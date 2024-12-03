package com.example.Sistema.de.Gerenciamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {
    @Autowired
    private RecursoRepository recursoRepository;

    public List<Recurso> listarTodos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }

    public List<Recurso> buscarPorTipo(String tipo) {
        return recursoRepository.findByTipo(tipo);
    }

    public Page<Recurso> listarTodosPaginados(Pageable pageable) {
        return recursoRepository.findAll(pageable);
    }

    public Recurso salvar(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public void excluir(Long id) {
        recursoRepository.deleteById(id);
    }
}