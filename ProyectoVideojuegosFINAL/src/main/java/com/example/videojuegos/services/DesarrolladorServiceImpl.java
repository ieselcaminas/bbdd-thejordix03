package com.example.videojuegos.service;

import com.example.videojuegos.model.Desarrollador;
import com.example.videojuegos.repository.DesarrolladorRepository;
import com.example.videojuegos.services.DesarrolladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesarrolladorServiceImpl implements DesarrolladorService {

    private final DesarrolladorRepository desarrolladorRepository;

    @Autowired
    public DesarrolladorServiceImpl(DesarrolladorRepository desarrolladorRepository) {
        this.desarrolladorRepository = desarrolladorRepository;
    }

    @Override
    public void crear(Desarrollador desarrollador) {
        desarrolladorRepository.save(desarrollador);
    }

    @Override
    public List<Desarrollador> obtenerTodos() {
        return desarrolladorRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        desarrolladorRepository.deleteById(id);
    }

    @Override
    public Optional<Desarrollador> obtenerPorId(Long id) {
        return desarrolladorRepository.findById(id);
    }
}
