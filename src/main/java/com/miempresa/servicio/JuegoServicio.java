package com.miempresa.servicio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.miempresa.interfaceServicio.IJuegoServicio;
import com.miempresa.interfaces.IJuego;
import com.miempresa.modelo.Juego;

@Service
public class JuegoServicio implements IJuegoServicio {
	@Autowired
    private IJuego repo;

    @Override
    public List<Juego> listar() {
        return (List<Juego>) repo.findAll();
    }

    @Override
    public Optional<Juego> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(Juego juego) {
        Juego juegoGuardado = repo.save(juego);
        return juegoGuardado != null ? 1 : 0;
    }

    @Override
    public void borrar(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Juego> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
}
