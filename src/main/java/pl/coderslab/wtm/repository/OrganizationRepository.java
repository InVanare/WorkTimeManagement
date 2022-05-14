package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import pl.coderslab.wtm.repository.entity.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
