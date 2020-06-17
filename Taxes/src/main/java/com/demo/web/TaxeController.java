package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dao.EntrepriseRepository;
import com.demo.entities.Entreprise;

@Controller
public class TaxeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@RequestMapping(value = "/entreprises", method = RequestMethod.GET)
	public String index(Model model,
			@RequestParam(name = "motCle", defaultValue = "")String motcle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Entreprise> pageEntreprise = entrepriseRepository.findByNom(
				"%"+motcle+"%", PageRequest.of(page, size));

		model.addAttribute("listEntreprise", pageEntreprise.getContent());
		int[] pages = new int[pageEntreprise.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motCle",motcle);
		return "entreprises";
	}

}
