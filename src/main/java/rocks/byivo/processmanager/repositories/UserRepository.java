package rocks.byivo.processmanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rocks.byivo.processmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("select u from User u where u.username = ?1")
    Optional<User> findUserByUsername(String username);
}
