package ibragim.project.core.springSecurityFinalProject.repositories;

import ibragim.project.core.springSecurityFinalProject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findRoleByRole(String role);
}
