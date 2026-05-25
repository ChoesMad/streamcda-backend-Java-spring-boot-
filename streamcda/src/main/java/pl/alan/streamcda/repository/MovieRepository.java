package pl.alan.streamcda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.alan.streamcda.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
}
