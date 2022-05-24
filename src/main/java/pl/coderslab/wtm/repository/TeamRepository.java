package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.dto.team.TeamDto;
import pl.coderslab.wtm.repository.entity.Team;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findByOwner(String name);
    List<Optional<Team>> findByName(String name);
    List<Optional<Team>> findAllByOwnerAndIsActive(User user, Boolean bool);
}
