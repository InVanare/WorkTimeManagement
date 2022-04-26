package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.repository.entity.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
