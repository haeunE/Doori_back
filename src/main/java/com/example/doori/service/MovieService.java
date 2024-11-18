package com.example.doori.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.doori.domain.Movie;
import com.example.doori.repository.MovieRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;

	public List<Movie> getMovieList(){
		return movieRepository.findAllByOrderByIdDesc();
	}
	
	private final String API_URL = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_xml2.jsp";
    private final String API_KEY = "6I52ZW551KNZ2Y10RM9J";

    public void fetchAndSaveMovies(String nation) {
        try {
            String apiUrl = API_URL + "?collection=nation_"+nation+"&detail=Y&nation="+nation+"&ServiceKey=" + API_KEY;
            URL url = new URL(apiUrl);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url.openStream());

            NodeList movieNodes = document.getElementsByTagName("Result");
            System.out.println(movieNodes);
//            List<Movie> movies = new ArrayList<>();
//
//            for (int i = 0; i < movieNodes.getLength() && i < 20; i++) {
//                Node node = movieNodes.item(i);
//                if (node.getNodeType() == Node.ELEMENT_NODE) {
//                    Element element = (Element) node;
//
//                    String title = element.getElementsByTagName("title").item(0).getTextContent();
//                    String director = element.getElementsByTagName("director").item(0).getTextContent();
//                    String genre = element.getElementsByTagName("genre").item(0).getTextContent();
//                    String releaseDate = element.getElementsByTagName("releaseDate").item(0).getTextContent();
//
//                    Movie movie = new Movie();
//                    movie.setTitle(title);
//                    movie.setDirector(director);
//                    movie.setGenre(genre);
//                    movie.setReleaseDate(releaseDate);
//
//                    movies.add(movie);
//                }
//            }

//            movieRepository.saveAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
