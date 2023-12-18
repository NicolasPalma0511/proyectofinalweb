package com.miempresa.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.miempresa.modelo.Juego;

@Repository 
public interface IJuego extends CrudRepository<Juego, Integer> {
}
