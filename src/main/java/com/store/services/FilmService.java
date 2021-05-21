package com.store.services;

import com.store.models.Film;

import java.util.List;

public interface FilmService {

	List<Film> findAll();
	
	Film findById(Integer id);
	
	void delete(Film film);
	
	long count();

	void save(Film film);

	boolean existsById(int id);

	void deleteById(int id);
	
	List<Object> getNumberFilmsPerYear();
	
	List<Film> getFilmsByStock(int lower, int upper);
}
