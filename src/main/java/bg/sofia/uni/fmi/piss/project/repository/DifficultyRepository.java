package bg.sofia.uni.fmi.piss.project.repository;

import bg.sofia.uni.fmi.piss.project.entity.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
}
