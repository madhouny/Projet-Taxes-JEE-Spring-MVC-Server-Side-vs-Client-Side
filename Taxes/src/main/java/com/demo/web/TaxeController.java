package com.demo.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.dao.EntrepriseRepository;
import com.demo.dao.TaxeRepository;
import com.demo.entities.Entreprise;
import com.demo.entities.Taxe;

@Controller
public class TaxeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private TaxeRepository taxeRepository;

	@RequestMapping(value = "/entreprises", method = RequestMethod.GET)
	public String index(Model model,
			@RequestParam(name = "motCle", defaultValue = "") String motcle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		Page<Entreprise> pageEntreprise = entrepriseRepository.findByNom("%"
				+ motcle + "%", PageRequest.of(page, size));

		model.addAttribute("listEntreprise", pageEntreprise.getContent());
		int[] pages = new int[pageEntreprise.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motCle", motcle);
		return "entreprises";
	}

	@RequestMapping(value = "/formEntreprise")
	public String formEntreprise(Model model) {
		model.addAttribute("entreprise", new Entreprise());
		return "formEntreprise";
	}

	@RequestMapping(value = "/saveEntreprise")
	public String save(Model model, @Validated Entreprise e, BindingResult er) {
		if (er.hasErrors())
			return "formEntreprise";

		entrepriseRepository.save(e);
		return "redirect:/entreprises";
	}

	@RequestMapping(value = "/taxes")
	public String taxes(Model model,
			@RequestParam(name = "code", defaultValue = "0") Long code) {
		model.addAttribute("entreprise", entrepriseRepository.findAll());
		if (code == 0) {
			model.addAttribute("taxes", new ArrayList<Taxe>());
		} else {
			Entreprise e = new Entreprise();
			e.setCode(code);

			model.addAttribute("taxes", taxeRepository.findByEntreprise(e));
		}

		return "taxes";
	}

}
