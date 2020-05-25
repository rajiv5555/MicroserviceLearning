/**
 * 
 */
package io.zivame.MovieInfoServices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.zivame.MovieInfoServices.Model.Movie;

/**
 * @author rajivranjan
 *
 */
@RestController
public class MovieInfoServicesController {

	@GetMapping("/movie/{id}")
	public Movie getInfo(@PathVariable("id") String movieId) {
		return new Movie(movieId,"test movie");
	}
}
