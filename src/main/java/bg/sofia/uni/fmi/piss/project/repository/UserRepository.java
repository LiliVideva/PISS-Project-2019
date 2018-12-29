package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.Account;
import bg.sofia.uni.fmi.piss.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user LEFT JOIN FETCH user.account WHERE user.email = :email")
    User findByEmailAndFetchAllEagerly(@Param("email") String email);

    @Query("SELECT user FROM User user LEFT JOIN user.userRoles AS roles LEFT JOIN user.account AS account " +
            "WHERE account = :account AND roles.id NOT IN (:roleId)")
    List<User> findByAccountAndRoleNotIn(@Param("account") Account account, @Param("roleId") Long roleId);

    //User findByEmail(String email);

    boolean existsByEmail(String email);
}
