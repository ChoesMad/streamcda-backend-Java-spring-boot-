package pl.alan.streamcda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alan.streamcda.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
