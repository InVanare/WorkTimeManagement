package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.repository.entity.Presence;
import pl.coderslab.wtm.repository.entity.User;

import java.util.List;
import java.util.Optional;

public interface PresenceRepository extends CrudRepository<Presence, Long> {
    List<Optional<Presence>> findByUser(User user);
    Optional<Presence> findByUserAndFinished(User user, Boolean bool);
}
