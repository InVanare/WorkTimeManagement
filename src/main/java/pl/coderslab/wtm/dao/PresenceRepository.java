package pl.coderslab.wtm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.dao.entity.Presence;

@Repository
public interface PresenceRepository extends CrudRepository<Presence, Long> {
}
