package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    // Map<String,Movie>
    //Map<String,Director>
    //Map<DirectorName,List>
    Map<String,Movie> movieDb=new HashMap<>();
    Map<String,Director> directorDb=new HashMap<>();
    Map<String,List<String>> directorMovieDb=new HashMap<>();
    public String addMovie(Movie movie)
    {
        movieDb.put(movie.getName(),movie);
        return "success";
    }


    public String addDirector(Director director)
    {
        directorDb.put(director.getName(),director);
        return "success";
    }


    public String addMovieDirectorPair(String movieName, String directorName)
    {
        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName))
        {
            List<String> list=new ArrayList<>();
            if(directorMovieDb.containsKey(directorName))
            {
                list=directorMovieDb.get(directorName);
                list.add(movieName);
                directorMovieDb.put(directorName,list);
                return "paired successful";
            }
            else {
                list.add(movieName);
                directorMovieDb.put(directorName,list);
                return "success";
            }


        }
        return "unsuccessful";
    }


    public Movie getMovieByName( String movieName)
    {

        return movieDb.get(movieName);
    }


    public Director getDirectorByName(String directorName)
    {
        return directorDb.get(directorName);
    }


    public List<String> getMoviesByDirectorName(String directorName)
    {
        return directorMovieDb.get(directorName);
    }


    public List<String> findAllMovies()
    {
        List<String> list=new ArrayList<>();
        for(String data:movieDb.keySet())
        {
            list.add(data);
        }
        return list;
    }


    public String deleteDirectorByName(String directorName)
    {
        List<String> list=new ArrayList<>();
        if(directorMovieDb.containsKey(directorName))
        {

            list=directorMovieDb.get(directorName);
            for(String data:list)
            {
                if(movieDb.containsKey(data))
                {
                    movieDb.remove(data);
                }
            }
            directorMovieDb.remove(directorName);
            if(directorDb.containsKey(directorName)) directorDb.remove(directorName);
        }
        return "success";
    }


    public String deleteAllDirectors()
    {

        for(String directorName: directorMovieDb.keySet())
        {
            deleteDirectorByName(directorName);
        }
//        for(String directorName : directorDb.keySet()) directorDb.rem
        directorDb.clear();

        return "success";
    }

}
