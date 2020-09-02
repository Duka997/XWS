package xml.team7.porukaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xml.team7.porukaservice.model.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(Long id);
    List<User> findByLastPasswordResetDate(Timestamp lastPasswordResetDate);

    List<User> findAllByDeleted(boolean b);
}