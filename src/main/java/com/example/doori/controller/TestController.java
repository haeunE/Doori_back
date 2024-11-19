package com.example.doori.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.doori.domain.Movie;
import com.example.doori.repository.MovieRepository;
import com.google.gson.Gson;

@Controller
public class TestController {
	@Autowired
		private MovieRepository movieRepository;
	
	@GetMapping("/doori/test")
	   public ResponseEntity<?> test() {
		
		return new ResponseEntity<>(test3(), HttpStatus.OK);
		
	   }
	   
	   public List<Map<?, ?>> test3() {
	      String url = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?";
	      url += "nation=대한민국";
	      url += "&createDts=2020";
	      url += "&listCount=20";
//	      ------------------------------
	      url += "&detail=Y";
	      url += "&collection=kmdb_new2";
	      url += "&ServiceKey=6I52ZW551KNZ2Y10RM9J";
	      url += "&type=극영화";
//	      url += "&sort=RANK";
	      
	      RestTemplate restTemplate = new RestTemplate();
	      
	      String response = restTemplate.getForObject(url, String.class);
	      
	      Gson gson = new Gson();
	      
	      Map<?, ?> json = gson.fromJson(response, Map.class);
	      System.out.println(json);
	      
	      List<Map<?, ?>> data = (List<Map<?, ?>>) json.get("Data");
	      
	      data  = (List<Map<?, ?>>) data.get(0).get("Result");
	      
	      
	      List<Movie> movies = new ArrayList<>();
	      
	      System.out.println(data.get(0).get("title"));
	      
	      for (int i=0; i<data.size();i++) {
	    	  Map<?, ?> allplots = (Map<?, ?>) data.get(i).get("plots");
	    	  List<Map<?, ?>> plots = (List<Map<?, ?>>)allplots.get("plot");
	    	  System.out.println(plots);
	    	  
	    	  Map<?, ?> alldirectors = (Map<?, ?>) data.get(i).get("directors");
	    	  List<Map<?, ?>> directors = (List<Map<?, ?>>)alldirectors.get("director");
	    	  System.out.println(directors);
	    	  
	    	  Map<?, ?> allactors = (Map<?, ?>) data.get(i).get("actors");
	    	  List<Map<?, ?>> actors = (List<Map<?, ?>>)allactors.get("actor");
	    	  String actor = "";
	    	  
	    	  for (int j=0; j<actors.size();j++) {
	    		  if(j==0) 
	    			  actor=(String)actors.get(0).get("actorNm");
	    		  else 
	    			  actor+=" "+(String)actors.get(j).get("actorNm");
	    	  }
	    	  System.out.println(actor);
	    	  Movie movie = new Movie();
	    	  String title = Optional.ofNullable((String)data.get(i).get("title")).orElse("0");
	    	  movie.setTitle(title);
	    	  System.out.println("제목 : "+title);
	    	  
	    	  String genre = Optional.ofNullable((String)data.get(i).get("genre")).orElse("0");
	    	  movie.setGenre(genre);
	    	  System.out.println("장르 : "+genre);
	    	  
	    	  String poster = Optional.ofNullable((String)data.get(i).get("posters")).orElse("0");
	    	  if (poster == null || poster.trim().isEmpty()) {
	    		    poster = "/img/non_img.png";
	    		}
	    	  movie.setMoviePoster(poster);
	    	  System.out.println("포스터 : "+poster);
	    	  System.out.println(poster.length());
	    	  
	    	  String runtime = Optional.ofNullable((String)data.get(i).get("runtime")).orElse("0");
	    	  movie.setRunningtime(runtime);
	    	  System.out.println("상영시간 : "+runtime );
	    	  
	    	  String director = Optional.ofNullable((String)directors.get(0).get("directorNm")).orElse("0");
	    	  movie.setDirector(director);
	    	  System.out.println("감독 : "+director);
	    	  
	    	  String plot = Optional.ofNullable((String) plots.get(0).get("plotText")).orElse("0");
	    	  movie.setPlot(plot);
	    	  System.out.println("줄거리 : "+plot);
	    	  
	    	  String date = Optional.ofNullable((String)data.get(i).get("regDate")).orElse("0");
	    	  movie.setReleaseDte(date);
	    	  System.out.println("등록일: "+date);
	    	  
	    	  String rate = (String)data.get(i).get("rating");
	    	  if (rate == null || rate.trim().isEmpty()) {
	    		  rate = "all";
	    		}
	    	  movie.setRatedYn(rate);
	    	  System.out.println("관람가 : "+rate);
	    	  
	    	  movie.setActor(actor);
	    	  System.out.println("배우 : "+actor);
	    	  
	    	  System.out.println(movie);
	    	  
	    	  System.out.println("-----------------------------------");
	    	  movieRepository.save(movie);
	    	  
	      }
	      System.out.println(data);
	      
	      return data;
	  }
	   
}
