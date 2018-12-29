package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /*List<Task> findByAccount(Account account);

    Task findByTitle(String title);

    List<Task> findByPartAndDifficulty(Part part, Difficulty difficulty);

    List<Task> findBySolutionContentIsNull();

    List<Task> findByPart(Part part);

    boolean existsByIdAndAccount(Long id, Account account);

    boolean existsByTitle(String title);*/

    boolean existsByPartAndDifficulty(Part part, Difficulty difficulty);
}
