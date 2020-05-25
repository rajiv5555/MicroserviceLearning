/**
 * 
 */
package io.zivame.MovieCatalogServices.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.zivame.MovieCatalogServices.Model.CatalogItem;
import io.zivame.MovieCatalogServices.Model.Movie;
import io.zivame.MovieCatalogServices.Model.Rating;
import io.zivame.MovieCatalogServices.Model.UserRating;

/**
 * @author rajivranjan
 *
 */
@RestController
public class MovieController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@GetMapping("/v1/catalog/{userId}")
	public List<CatalogItem> getCatalogV1(@PathVariable("userId") String userId){

		List<Rating> ratings= Arrays.asList(
		  new Rating("1234", 4+""),
		  new Rating("456", 5+"")
		);
		
		return ratings.stream().map(rating->{
			Movie movie=restTemplate.getForObject("http://localhost:8082/movie/"+rating.getMovieId(),Movie.class);
		    return new CatalogItem(movie.getName()," good movie ", rating.getRatingNo());
		}).collect(Collectors.toList());
		
		//return Collections.singletonList(new CatalogItem("Titanic", "Test", 4));
	}
	
	@GetMapping("/v1/reactiveCatalog/{userId}")
	public List<CatalogItem> getReactiveCatalogV1(@PathVariable("userId") String userId){

		List<Rating> ratings= Arrays.asList(
		  new Rating("1234", 4+""),
		  new Rating("456", 5+"")
		);
		
		return ratings.stream().map(rating->{
			//Movie movie=restTemplate.getForObject("http://localhost:8082/movie/"+rating.getMovieId(),Movie.class);
		    Movie movie= webClientBuilder.build()
		                     .get()
		                     .uri("http://localhost:8082/movie/"+rating.getMovieId())
		                     .retrieve()
		                     .bodyToMono(Movie.class)
		                     .block();
			return new CatalogItem(movie.getName()," good movie ", rating.getRatingNo());
		}).collect(Collectors.toList());
		
		//return Collections.singletonList(new CatalogItem("Titanic", "Test", 4));
	}
	
	@GetMapping("/v2/catalog/{userId}")
	public List<CatalogItem> getCatalogV2(@PathVariable("userId") String userId){

		/*
		 * List<Rating> ratings= Arrays.asList( new Rating("1234", 4+""), new
		 * Rating("456", 5+"") );
		 */
		
		UserRating ratings=restTemplate.getForObject("http://localhost:8083/ratings/"+userId,UserRating.class);
		
		return ratings.getUserRatings().stream().map(rating->{
			Movie movie=restTemplate.getForObject("http://localhost:8082/movie/"+rating.getMovieId(),Movie.class);
		    return new CatalogItem(movie.getName()," good movie ", rating.getRatingNo());
		}).collect(Collectors.toList());
		
		//return Collections.singletonList(new CatalogItem("Titanic", "Test", 4));
	}
	
	@GetMapping("/v2/reactiveCatalog/{userId}")
	public List<CatalogItem> getReactiveCatalogV2(@PathVariable("userId") String userId){

		/*List<Rating> ratings= Arrays.asList(
		  new Rating("1234", 4+""),
		  new Rating("456", 5+"")
		);*/
		UserRating ratings=webClientBuilder.build()
				                          .get()
				                          .uri("http://localhost:8083/ratings/"+userId)
				                          .retrieve()
				                          .bodyToMono(UserRating.class)
				                          .block();
		
		return ratings.getUserRatings().stream().map(rating->{
			//Movie movie=restTemplate.getForObject("http://localhost:8082/movie/"+rating.getMovieId(),Movie.class);
		    Movie movie= webClientBuilder.build()
		                     .get()
		                     .uri("http://localhost:8082/movie/"+rating.getMovieId())
		                     .retrieve()
		                     .bodyToMono(Movie.class)
		                     .block();
			return new CatalogItem(movie.getName()," good movie ", rating.getRatingNo());
		}).collect(Collectors.toList());
		
		//return Collections.singletonList(new CatalogItem("Titanic", "Test", 4));
	}
}