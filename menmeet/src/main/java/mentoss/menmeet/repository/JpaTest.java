package mentoss.menmeet.repository;

import mentoss.menmeet.entity.MentoringPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTest extends JpaRepository<MentoringPost, Integer> {
}
