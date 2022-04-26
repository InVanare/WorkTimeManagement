package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.repository.entity.Presence;

@Repository
public interface PresenceRepository extends CrudRepository<Presence, Long> {
}
