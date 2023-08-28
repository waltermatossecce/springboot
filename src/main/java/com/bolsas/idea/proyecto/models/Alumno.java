package com.bolsas.idea.proyecto.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="alumno")
@Getter
@Setter
public class Alumno implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alumno")
	private Long id;
	@NotEmpty
	@Size(min = 4,max = 12)
	@Column(nullable = false)
	private String nombre;
	@NotEmpty
	@Column(nullable = false)
	private String apellido;
	@NotEmpty
	@Email
	@Column(nullable = false,unique = true)
	private String correo;
	private int edad;
	private char sexo;
	@Temporal(TemporalType.DATE)
	@Column(name="create_At")
	private Date create_at;
	@PrePersist
	public void prePersist() {
		this.create_at=new Date();
	}
	private static final long serialVersionUID = 1L;
}
