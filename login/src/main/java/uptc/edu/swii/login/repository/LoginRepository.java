package uptc.edu.swii.login.repository;

import uptc.edu.swii.login.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByCustomerid(String customerid);
}