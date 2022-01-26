package br.com.eliel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eliel.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);
	
}