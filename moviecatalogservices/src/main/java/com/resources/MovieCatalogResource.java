package com.resources;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.model.CatalogItem;
import com.model.Movie;
import com.model.Rating;
import com.model.UserRating;



@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@Autowired 
private	RestTemplate restTemplate;
	@Autowired 
	private 	WebClient.Builder WebClientBuilder;
	
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
UserRating ratings=restTemplate.getForObject("http://ratingsdataservice/ratingsdata/users/" +userId,UserRating.class);
				
	return	ratings.getUserRating().stream().map(rating->{
	Movie movie=	restTemplate.getForObject("http://movieinfoservice/movies/ "+rating.getMovieId(),Movie.class);
			/*
			 * Movie movie= WebClientBuilder.build(). get().
			 * uri("http://localhost:8082/movies/ "+rating.getMovieId()) .retrieve()
			 * .bodyToMono(Movie.class) .block();
			 */
	
		return new CatalogItem( movie.getName(),"desc",rating.getRating());
			
		
	})
		.collect(Collectors.toList());
		
		
		
	
	
}

}
