package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByPartAndDifficulty(Part part, Difficulty difficulty);

  List<Task> findByPartIsNullAndDifficultyIsNull();

  boolean existsByTitle(String title);
}
