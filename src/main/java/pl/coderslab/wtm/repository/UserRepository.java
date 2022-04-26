package pl.coderslab.wtm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.repository.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
