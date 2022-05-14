package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.repository.entity.Presence;

public interface PresenceRepository extends CrudRepository<Presence, Long> {
}
