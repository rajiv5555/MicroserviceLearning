/**
 * 
 */
package io.zivame.RatingDataServices.Model;

/**
 * @author rajivranjan
 *
 */
public class Rating {

	private String movieId;
	private String ratingNo;
	
	public Rating() {
		super();
	}
	
	

	public Rating(String movieId, String ratingNo) {
		this.movieId = movieId;
		this.ratingNo = ratingNo;
	}



	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getRatingNo() {
		return ratingNo;
	}

	public void setRatingNo(String ratingNo) {
		this.ratingNo = ratingNo;
	}
	
	

}
