package com.bolsas.idea.proyecto.service;

import java.util.List;

import com.bolsas.idea.proyecto.models.Alumno;

public interface AlumnoService {

	public List<Alumno>findAll();
	public Alumno findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);
}
