package com.atv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atv.entities.Curso;

public interface CurRepository extends JpaRepository <Curso, Long> {
	

}
