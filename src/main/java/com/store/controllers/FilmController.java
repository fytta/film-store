package com.store.controllers;

import com.google.gson.Gson;
import com.store.models.Film;
import com.store.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
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

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/films/add")
	public ResponseEntity add(@RequestBody Film film) {

		return new ResponseEntity("Film added!", HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/films/update")
	public ResponseEntity update(@RequestBody Film film) {

		System.out.println(film.getDescription());
		filmService.save(film);
		return new ResponseEntity("Film "+film.getId()+" updated!", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/films/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") int id) {

		filmService.deleteById(id);
		return new ResponseEntity("Film "+id +" deleted!", HttpStatus.OK);
	}
}
