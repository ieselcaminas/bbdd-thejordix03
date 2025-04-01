package com.example.videojuegos.services;

import com.example.videojuegos.model.Plataforma;
import com.example.videojuegos.repository.PlataformaRepository;
import com.example.videojuegos.services.PlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaServiceImpl implements PlataformaService {

    private final PlataformaRepository plataformaRepository;

    @Autowired
    public PlataformaServiceImpl(PlataformaRepository plataformaRepository) {
        this.plataformaRepository = plataformaRepository;
    }

    @Override
    public void crear(Plataforma plataforma) {
        plataformaRepository.save(plataforma);
    }

    @Override
    public List<Plataforma> obtenerTodos() {
        return plataformaRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        plataformaRepository.deleteById(id);
    }

    @Override
    public Optional<Plataforma> obtenerPorId(Long id) {
        return plataformaRepository.findById(id);
    }
}
