package pl.coderslab.wtm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.dao.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}
