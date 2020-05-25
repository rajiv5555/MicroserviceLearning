/**
 * 
 */
package io.zivame.RatingDataServices.Model;

import java.util.List;

/**
 * @author rajivranjan
 *
 */
public class UserRating {
	
	private List<Rating> userRatings;

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}
	
	

}
