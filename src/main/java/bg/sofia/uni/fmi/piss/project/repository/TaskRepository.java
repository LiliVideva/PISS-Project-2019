package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import bg.sofia.uni.fmi.piss.project.entity.Part;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByPartAndDifficulty(Part part, Difficulty difficulty);

    List<Task> findByPartIsNullAAndDifficultyIsNull();

    boolean existsByTitle(String title);
}
