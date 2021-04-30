package com.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.store.models.Film;
import com.store.services.FilmService;

@CrossOrigin() // Descomentar para aceptar peticiones *.*.*.*
@RestController
@RequestMapping("/api/v1")
public class FilmController {

	@Autowired
	FilmService filmService;
		
	private final Gson gson = new Gson();
	
	@GetMapping("/films")
	public ResponseEntity<List<Film>> getFilms() {
		
		List<Film> films = filmService.findAll();
		
		return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
	}
	
	@GetMapping("/films/{id}")
	public ResponseEntity<Film> getFilm(@PathVariable("id") int id) {
		
		Film film = filmService.findById(id);
		
		return new ResponseEntity<Film>(film, HttpStatus.OK);
	}
	
	@GetMapping("/films/release")
	public ResponseEntity<String> getNumberFilmsPerYear() {
		
		List<Object> objects = filmService.getNumberFilmsPerYear();

		String json = gson.toJson(objects);
		
		return new ResponseEntity<String>(json, HttpStatus.OK);
		
	}
	
	@GetMapping("/films/count")
	public ResponseEntity<Long> count() {
		
		Long count = filmService.count();
		
		return new ResponseEntity<Long>(count, HttpStatus.OK);
		
	}
	
	@GetMapping("/films/stock/{lower}-{upper}")
	public ResponseEntity<List<Film>> getFilmsByStock(@PathVariable("lower") int lower, @PathVariable("upper") int upper) {
		
		List<Film> films = filmService.getFilmsByStock(lower, upper);

		return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
	}
	

}
