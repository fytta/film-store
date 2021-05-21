package com.store.services;

import com.google.gson.Gson;
import com.store.models.Film;
import com.store.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public void save(Film film) {
		filmRepository.save(film);
	}

	@Override
	public boolean existsById(int id) {
		return filmRepository.existsById(id);
	}

	@Override
	public void deleteById(int id) {
		filmRepository.deleteById(id);
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
