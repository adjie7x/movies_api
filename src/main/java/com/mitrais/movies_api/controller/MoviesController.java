package com.mitrais.movies_api.controller;


import com.mitrais.movies_api.model.ResponseBody;
import com.mitrais.movies_api.service.RestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviesController {

    RestClientService restClientService = new RestClientService();


    @GetMapping("/movies/find")
    public ResponseBody findMovies(@RequestParam("title") String title, @RequestParam("page_number") Long pageNumber){

        ResponseBody response =restClientService.searchMovies(title,pageNumber);
        return response;

    }

    @GetMapping("/movies/search")
    public List<String> searchMovies(@RequestParam("title") String title){



        return restClientService.getAllMovieTitles(title);

    }

}
