package com.mitrais.movies_api.service;

import com.mitrais.movies_api.model.Movie;
import com.mitrais.movies_api.model.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestClientService {

    private static final String REST_URI = "https://jsonmock.hackerrank.com/api/movies/search/";
    private Client client = ClientBuilder.newClient();

    public ResponseBody searchMovies(String title, long pageNumber) {

        ResponseBody response = client.target(REST_URI).queryParam("Title",title).queryParam("page",pageNumber).request(MediaType.APPLICATION_JSON).get(ResponseBody.class);

        return response;
    }

    public List<String> getAllMovieTitles(String title){
        ResponseBody restResponse = searchMovies(title,1);
        List<String> response = new ArrayList<>();
        List<Movie> movies = restResponse.getData();
        if(restResponse != null){
            if(restResponse.getData().size() > 0 && restResponse.getTotal_pages() > 1){
                for (long i = 2; i <= restResponse.getTotal_pages();i++){
                    restResponse = searchMovies(title, i);
                    movies.addAll(restResponse.getData());
                }
            }
        }

        for (Movie movie: movies) {
            response.add(movie.getTitle());
        }

        Collections.sort(response);

        return response;
    }

}