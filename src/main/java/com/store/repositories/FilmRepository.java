package com.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.store.models.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	@Query(value="SELECT * FROM film WHERE stock between ?1 AND ?2", nativeQuery = true)
	List<Film> getFilmsByStock(int lower, int upper);
	
	@Query(value="SELECT release_year, count(*) FROM film GROUP BY release_year;", nativeQuery = true )
	List<Object> getNumberFilmsPerYear();
	
}
