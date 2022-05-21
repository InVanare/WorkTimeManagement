package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.repository.entity.Organization;
import pl.coderslab.wtm.repository.entity.User;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    Optional<Organization> findByName(String name);
    Optional<Organization> findByOwnerAndIsActive(User user, Boolean bool);
}
