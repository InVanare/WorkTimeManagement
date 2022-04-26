package pl.coderslab.wtm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.wtm.repository.OrganizationRepository;
import pl.coderslab.wtm.repository.entity.Organization;

import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Optional<Organization> findById(Long id) {
        return organizationRepository.findById(id);
    }

    public Iterable<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public void deleteById(Long id) {
        organizationRepository.deleteById(id);
    }

}
