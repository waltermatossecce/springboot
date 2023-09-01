package com.bolsas.idea.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.loader.custom.ResultRowProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsas.idea.proyecto.models.Alumno;
import com.bolsas.idea.proyecto.service.AlumnoService;



@RestController
@RequestMapping(path = "api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AlumnoRestController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/clientes")
	public List<Alumno> index() {
		return alumnoService.findAll();
	}
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		
		Alumno alumno = null;

		Map<String,Object>resultado=new HashMap<>();
		try {
         alumno= alumnoService.findById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
		  resultado.put("mensaje", "Error al hacer una consulta en la base de datos");
		  resultado.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		  return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (alumno==null) {
			resultado.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no éxiste en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Alumno>(alumno,HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Alumno alumno) {
		
		
		Alumno nuevoalumno=null;
		Map<String, Object>response=new HashMap<>();
		
		
		try {
			nuevoalumno=alumnoService.save(alumno);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al hacer un insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		    return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El alumno se ha creado con éxito!");
		response.put("alumno", nuevoalumno);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
//		Alumno nuevoAlumno =null;
//		Map<String, Object>response=new HashMap<>();
//		try {
//			
//			nuevoAlumno=alumnoService.save(alumno);
//			
//		} catch (DataAccessException e) {
//			// TODO: handle exception
//			response.put("mensaje", "Error al hacer un insert en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El cliente se ha creado con éxito!");
//		response.put("alumno", nuevoAlumno);
//
//		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Alumno alumno,@PathVariable Long id) {
		
		Alumno alumnoActual=alumnoService.findById(id);
		
		Alumno alumnoUpdated=null;
		
		
		Map<String, Object>resultado=new HashMap<>();
		
		if(alumnoActual==null) {
			resultado.put("mensaje", "Error: no se puede editar, el alumno ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
		    return new ResponseEntity<Map<String, Object>>(resultado,HttpStatus.NOT_FOUND);
		}
		try {
			alumnoActual.setNombre(alumno.getNombre());
			alumnoActual.setApellido(alumno.getApellido());
			alumnoActual.setCorreo(alumno.getCorreo());
			alumnoActual.setEdad(alumno.getEdad());
			alumnoActual.setSexo(alumno.getSexo());
			
			alumnoUpdated = alumnoService.save(alumnoActual);
			
		} catch (DataAccessException e) {
			// TODO: handle exception
			resultado.put("mensaje", "Error al actualizar el alumno en la base de datos");
			resultado.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		resultado.put("mensaje", "El alumno se ha actualizado con éxito!");
		resultado.put("alumno", alumnoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(resultado,HttpStatus.CREATED); 
		
	}
	
	@DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object>resultado=new HashMap<>();
		
		try {
			alumnoService.deleteById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			resultado.put("mensaje", "Error al eliminar el alumno en la base de datos");
			resultado.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resultado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(resultado,HttpStatus.OK);
		

	}
}