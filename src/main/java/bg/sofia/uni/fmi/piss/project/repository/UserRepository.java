package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user LEFT JOIN user.userRoles AS roles WHERE roles.id NOT IN (:roleId)")
    List<User> findByRoleNotIn(@Param("roleId") Long roleId);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
