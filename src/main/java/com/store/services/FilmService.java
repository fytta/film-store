package com.store.services;

import java.util.List;

import com.store.models.Film;

public interface FilmService {

	List<Film> findAll();
	
	Film findById(Integer id);
	
	void delete(Film film);
	
	long count();
	
	List<Object> getNumberFilmsPerYear();
	
	List<Film> getFilmsByStock(int lower, int upper);
}
