package com.miempresa.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="juego")
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 255, message = "El nombre no puede tener más de 255 caracteres")
    @Column
    private String nombre;

    @NotBlank(message = "La descripción no puede estar en blanco")
    @Size(message = "La descripción no puede tener más de 255 caracteres")
    @Column
    private String descripcion;

    @NotBlank(message = "La categoría no puede estar en blanco")
    @Size(max = 255, message = "La categoría no puede tener más de 255 caracteres")
    @Column
    private String categoria;
	
	public Juego() {
   	 	
    }

	public Juego(int id, String nombre, String descripcion, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
