/**
 * 
 */
package io.zivame.MovieCatalogServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.zivame.MovieCatalogServices.Model.Movie;

/**
 * @author rajivranjan
 *
 */
@Service
public class MovieService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;

	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallBack")
	public Movie getMovieInforestTemplate(String movieId) {
		return restTemplate.getForObject("http://MovieInfoService/movie/"+movieId,Movie.class);
	}
	
	private Movie getMovieInfoFallBack(String movieId) {
		return new Movie("0", "dummy");
	}
	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallBack")
	public Movie getMovieInfoWebClient(String movieId) {
		return webClientBuilder.build()
        .get()
        .uri("http://MovieInfoService/movie/"+movieId)
        .retrieve()
        .bodyToMono(Movie.class)
        .block();
	}
}
