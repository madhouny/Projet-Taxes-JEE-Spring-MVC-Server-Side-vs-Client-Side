package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Long>{

}
