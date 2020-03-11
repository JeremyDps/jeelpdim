package jeremy.jeelpdim.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jeremy.jeelpdim.DTO.DresseurDTO;
import jeremy.jeelpdim.services.ILogService;

@RestController //déclarer comme étant un controlleur Rest
@RequestMapping(value = "/dresseurs")

public class DresseursController {
	
	private List<DresseurDTO> dresseurs = new ArrayList<>();
	
	@Autowired
	private ILogService logService;
	
	public DresseursController() {
		
		DresseurDTO d1 = new DresseurDTO();
		d1.setId(1);
		d1.setNom("Sacha");	
		
		DresseurDTO d2 = new DresseurDTO();
		d2.setId(2);
		d2.setNom("Red");		
		
		DresseurDTO d3 = new DresseurDTO();
		d3.setId(3);
		d3.setNom("Blue");
		
		DresseurDTO d4 = new DresseurDTO();
		d4.setId(4);
		d4.setNom("Ondine");
		
		this.dresseurs.add(d1);
		this.dresseurs.add(d2);
		this.dresseurs.add(d3);
		this.dresseurs.add(d4);
	}
	
	@GetMapping(value = "/{id}")
	public DresseurDTO findById(Integer id) {
		return this.dresseurs.stream()
				.filter(dresseur -> dresseur.getId().equals(id))
				.findFirst().orElse(null);
	}
	
	// http://localhost:8080/dresseurs
	@GetMapping
	public List<DresseurDTO> getDresseurs() {
		return this.dresseurs;
	}
	
	// http://localhost:8080/dresseurs/3
	@GetMapping(value = "/{id}")
	public DresseurDTO getDresseursById(@PathVariable int id) {
		return this.dresseurs.stream()
				.filter(dresseur -> dresseur.getId().equals(id))
				.findFirst().orElse(null);
	}
	
	
	// http://localhost:8080/dresseurs/search?id=3
	@GetMapping(value = "/search")
	public DresseurDTO getDresseursBySearch(@RequestParam(required = false) Integer id, 
			@RequestParam(required = false) String name) {
		if (id != null) {
			return this.dresseurs.stream()
					.filter(dresseur -> dresseur.getId().equals(id))
					.findFirst().orElse(null);
		}
		
		if(name != null) {
			return this.dresseurs.stream()
					.filter(dresseur -> dresseur.getNom().equals(name))
					.findFirst().orElse(null);
		}
		
		return null;
	}
	
	// sur postman en delete : localhost:8080/dresseurs?id=3
	@DeleteMapping
	public ResponseEntity deleteDresseurBySearch(
			@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String name) {
		if(id != null) {
			this.dresseurs.removeIf(dresseur -> dresseur.getId().equals(id));
			return ResponseEntity.noContent().build();
		}
		
		if(name != null) {
			this.dresseurs.removeIf(dresseur -> dresseur.getNom().equals(name));
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	} 
	
	// sur postman en post : localhost:8080/dresseurs
	@PostMapping
	public void postDresseur() {
		// recherche l'id max + 1
		Integer newMaxId = this.dresseurs.stream().map(DresseurDTO::getId)
				.mapToInt(Integer::intValue)
				.max().orElse(0)
				+ 1;
		DresseurDTO d = new DresseurDTO();
		d.setId(newMaxId);
		d.setNom("Jean");
		this.dresseurs.add(d);
	}
	
	// sur postman an put : localhost:8080/dresseurs/5?name=Mathieu
	@PutMapping(value = "/{id}") 
	public void putDresseur(
			@PathVariable int id,
			@RequestParam(required = false) String name) {
		DresseurDTO d = this.dresseurs.stream()
				.filter(dresseur -> dresseur.getId().equals(id))
				.findFirst().orElse(null);
		
		if (d != null) {
			if(name != null) {
				d.setNom(name);
			} else {
				if(d.getNom() == "Jean") {
				d.setNom("Marcel");
				} else {
					d.setNom("Jean");
				}
			}
		}
	}
}
