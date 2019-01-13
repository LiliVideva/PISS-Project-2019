package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String name);
}
