package com.atv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atv.entities.Curso;
import com.atv.services.CurServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Cursos", description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/curso")
public class CurController {
	
	private final CurServico curServico;
	
	@Autowired
	public CurController(CurServico curServico) {
		this.curServico = curServico;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza curso por ID")
	public ResponseEntity<Curso> buscaCursoControlId(@PathVariable Long id){
		Curso curso = curServico.buscaCursoId(id);
		if(curso != null) {
			return ResponseEntity.ok(curso);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os cursos")
	public ResponseEntity<List<Curso>> buscaTodosCursosControl(){
		List<Curso> cursos = curServico.buscaTodosCursos();
		return ResponseEntity.ok(cursos);
	}
	@PostMapping("/")
	@Operation (summary = "Cadastra um curso")
	public ResponseEntity<Curso> salvaCursosControl (@RequestBody @Valid Curso curso){
		Curso salvaCurso = curServico.salvaCurso(curso);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso);
	}
	@PutMapping("/{id}")
	@Operation (summary = "Altera curso")
	public ResponseEntity<Curso> alteraCursoControl (@PathVariable Long id, @RequestBody @Valid Curso curso){
		Curso alteraCurso = curServico.alterarCurso(id, curso);
		if(alteraCurso != null) {
			return ResponseEntity.ok(curso);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Exclui curso")
	public ResponseEntity<Curso> apagaCursoControl (@PathVariable Long id){
		boolean apagar = curServico.apagarCurso(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
