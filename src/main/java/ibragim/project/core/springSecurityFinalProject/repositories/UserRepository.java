package ibragim.project.core.springSecurityFinalProject.repositories;

import ibragim.project.core.springSecurityFinalProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}
