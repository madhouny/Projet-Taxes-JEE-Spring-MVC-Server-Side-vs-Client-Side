package com.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.dao.EntrepriseRepository;
import com.demo.dao.TaxeRepository;
import com.demo.entities.Entreprise;
import com.demo.entities.IR;
import com.demo.entities.TVA;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Entreprise e1 = entrepriseRepository.save(new Entreprise("BMCE", "bmce@gmail.com", "SARL"));
		Entreprise e2 = entrepriseRepository.save(new Entreprise("BP", "bP@gmail.com", "SARL"));
		
		taxeRepository.save(new TVA("TVA Habitation", new Date(), 2500, e1));
		taxeRepository.save(new TVA("TVA Voiture", new Date(), 500, e1));
		taxeRepository.save(new IR("IR 2020 ", new Date(), 1000, e1));
		taxeRepository.save(new IR("IR 2019", new Date(), 4000, e1));
		taxeRepository.save(new TVA("TVA FEU", new Date(), 200, e2));
		taxeRepository.save(new IR("IR 2019", new Date(), 5000, e2));
	}

}
