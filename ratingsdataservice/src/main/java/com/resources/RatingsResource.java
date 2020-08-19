package com.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Rating;
import com.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

	 @RequestMapping("/users/{userId}")
	 public UserRating getUserRating(@PathVariable("userId") String userId) { 
		 List<Rating> ratings=Arrays.asList(
					new Rating("1234",4),
					new Rating("1235",5)
					);
		 UserRating userRating=new UserRating();
		 userRating.setUserRating(ratings);
		return userRating;
		
	 }
}