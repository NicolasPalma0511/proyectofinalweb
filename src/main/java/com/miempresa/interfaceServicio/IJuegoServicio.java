package com.miempresa.interfaceServicio;

import java.util.List;
import java.util.Optional;
import com.miempresa.modelo.Juego;

public interface IJuegoServicio {
	List<Juego> listar();
    Optional<Juego> listarId(int id);
    int guardar(Juego juego);
    void borrar(int id);
}
