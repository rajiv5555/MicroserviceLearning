/**
 * 
 */
package io.zivame.MovieCatalogServices.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.zivame.MovieCatalogServices.Model.Rating;
import io.zivame.MovieCatalogServices.Model.UserRating;

/**
 * @author rajivranjan
 *
 */
@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@HystrixCommand(fallbackMethod = "getUserRatingFallBack")
	public UserRating getUserRatingWebClient(String userId) {
		return webClientBuilder.build()
                .get()
                .uri("http://MovieRatingService/ratings/"+userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();
	}
	

	@HystrixCommand(fallbackMethod = "getUserRatingFallBack")
	public UserRating getUserRatingrestTemplate(String userId) {
		return restTemplate.getForObject("http://MovieRatingService/ratings/"+userId,UserRating.class);
	}
	
	private UserRating getUserRatingFallBack(String userId) {
		UserRating raitngs= new UserRating();
		raitngs.setUserRatings(Arrays.asList(new Rating("101","0")));
		return raitngs;
	}
}
