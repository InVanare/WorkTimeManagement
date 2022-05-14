package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.repository.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
