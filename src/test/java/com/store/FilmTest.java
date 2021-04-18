package com.store;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.store.models.Film;
import com.store.services.FilmService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmTest {

	@Autowired
	private FilmService service;
	
	@Test
	public void filmsByStock() {

		List<Film> lst1 = service.getFilmsByStock(0, 0);
		List<Film> lst2 = service.getFilmsByStock(0, 3);
		List<Film> lst3 = service.getFilmsByStock(4, 6);
		List<Film> lst4 = service.getFilmsByStock(7, 10);
		
		System.out.println(String.format("%d %d %d %d", lst1.size(), lst2.size(), lst3.size(), lst4.size()));
	}
	
}
