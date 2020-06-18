package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Entreprise;
import com.demo.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Long>{

	public List<Taxe> findByEntreprise(Entreprise e);
}
