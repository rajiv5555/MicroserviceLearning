/**
 * 
 */
package io.zivame.RatingDataServices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.zivame.RatingDataServices.Model.Rating;
import io.zivame.RatingDataServices.Model.UserRating;

/**
 * @author rajivranjan
 *
 */
@RestController
public class RatingDataController {

	@GetMapping("rating/{movieId}")
	public Rating getRating(@PathVariable("movieId") String moviedId) {
		return new Rating(moviedId,4+"");
	}
	
	@GetMapping("ratings/{userId}")
	public UserRating getUserRating(){
		List<Rating> ratings= Arrays.asList(
				  new Rating("1234", 4+""),
				  new Rating("456", 5+"")
				);
		
		UserRating userRatings=new UserRating();
		userRatings.setUserRatings(ratings);
		return userRatings;
	}
}
