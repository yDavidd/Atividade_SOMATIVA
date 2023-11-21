package com.atv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atv.entities.Curso;
import com.atv.repository.CurRepository;

@Service
public class CurServico {
	
	private final CurRepository curRepository;
	
	@Autowired
	public CurServico (CurRepository curRepository) {
		this.curRepository = curRepository;
	}
	public List<Curso> buscaTodosCursos(){
		return curRepository.findAll();
		
	}
	public Curso buscaCursoId(Long id) {
		Optional<Curso> curso = curRepository.findById(id);
		return curso.orElse(null);
	}
	public Curso alterarCurso(Long id, Curso alterarCurso) {
		Optional <Curso> existeCurso = curRepository.findById(id);
		if(existeCurso.isPresent()) {
			alterarCurso.setId(id);
			return curRepository.save(alterarCurso);
		}
		return null;
	}
	public boolean apagarCurso(Long id) {
		Optional <Curso> existeCurso = curRepository.findById(id);
		if(existeCurso.isPresent()) {
			curRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public Curso salvaCurso(Curso curso) {
		return curRepository.save(curso);
	}

}
