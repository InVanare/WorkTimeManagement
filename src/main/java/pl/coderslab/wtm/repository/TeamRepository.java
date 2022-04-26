package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.repository.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}