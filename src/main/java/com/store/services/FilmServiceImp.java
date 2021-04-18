package com.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.store.models.Film;
import com.store.repositories.FilmRepository;

@Service
public class FilmServiceImp implements FilmService{
	
	@Autowired
	FilmRepository filmRepository;

	final static Gson gson = new Gson();
	
	@Override
	public List<Film> findAll() {
		
		List<Film> films = filmRepository.findAll();
		return films;
	}

	@Override
	public Film findById(Integer id) {	
		
		Film film = new Film();
		
		if (filmRepository.findById(id).isPresent()) {
			film = filmRepository.findById(id).get();
		}
		
		return film;
	}

	@Override
	public void delete(Film film) {
		filmRepository.delete(film);
	}

	@Override
	public long count() {
		return filmRepository.count();
	}

	@Override
	public List<Object> getNumberFilmsPerYear() {
		
		List<Object> objects = filmRepository.getNumberFilmsPerYear();
		
		return objects;
	}

	@Override
	public List<Film> getFilmsByStock(int lower, int upper) {
		List<Film> films = filmRepository.getFilmsByStock(lower, upper);
		return films;
	}


	
}
