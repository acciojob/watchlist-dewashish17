package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
       // System.out.println("movie name is "+movie.getName());
        String response=movieService.addMovie(movie);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director)
    {
        //System.out.println("director name is "+director.getName());
        String response=movieService.addDirector(director);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("Mname") String movieName,@RequestParam("Dname") String directorName)
    {
        //System.out.println("movie name is "+movieName+" directorName is "+directorName);
        String response =movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName)
    {
       // System.out.println("movie name is "+movieName);
        Movie response =movieService.getMovieByName(movieName);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName)
    {
        //System.out.println("director name is "+directorName);
        Director response=movieService.getDirectorByName(directorName);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName)
    {
        //System.out.println("get movies by director  name is "+directorName);
        List response=movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity(response,HttpStatus.FOUND);

    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies()
    {
        //System.out.println("get all movie");
        List response = movieService.findAllMovies();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("Dname") String directorName)
    {
        //System.out.println("delete director "+directorName);
        String response=movieService.deleteDirectorByName(directorName);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors()
    {
        //System.out.println("delete all director");
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
