package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.EntrepriseRepository;
import com.demo.entities.Entreprise;

@RestController
public class TaxeRestController {
	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@RequestMapping("/listEntreprise")
	public Page<Entreprise> list(
			@RequestParam(name = "motCle", defaultValue = "") String motCle,
			@RequestParam(name = "page", defaultValue = "0")int page, 
			@RequestParam(name = "size", defaultValue = "3")int size) {
		return entrepriseRepository.findByNom("%"+motCle+"%", PageRequest.of(page, size));
	}
}
