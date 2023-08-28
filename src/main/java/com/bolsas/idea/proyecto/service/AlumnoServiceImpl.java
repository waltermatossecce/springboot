package com.bolsas.idea.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsas.idea.proyecto.models.Alumno;
import com.bolsas.idea.proyecto.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findAll() {
		// TODO Auto-generated method stub
		return (List<Alumno>) alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Alumno findById(Long id) {
		// TODO Auto-generated method stub
		return alumnoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		// TODO Auto-generated method stub
		return alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		alumnoRepository.deleteById(id);
	}

}
