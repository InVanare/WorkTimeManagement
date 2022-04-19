package pl.coderslab.wtm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.wtm.dao.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
