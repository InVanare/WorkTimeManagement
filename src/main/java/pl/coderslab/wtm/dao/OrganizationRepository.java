package pl.coderslab.wtm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.dao.entity.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
