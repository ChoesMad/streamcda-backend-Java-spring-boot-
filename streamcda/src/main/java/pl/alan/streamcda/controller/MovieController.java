package pl.alan.streamcda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alan.streamcda.entity.Movie;
import pl.alan.streamcda.repository.MovieRepository;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {
     @Autowired
     private MovieRepository movieRepository;

     @GetMapping
     public List<Movie> getAllMovies(){
         return movieRepository.findAll();
     }

     @PostMapping
     public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
         Movie savedMovie = movieRepository.save(movie);
         return ResponseEntity.ok(savedMovie);
     }
}
