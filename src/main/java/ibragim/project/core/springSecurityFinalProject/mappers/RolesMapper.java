package ibragim.project.core.springSecurityFinalProject.mappers;


import ibragim.project.core.springSecurityFinalProject.dto.RoleDTO;
import ibragim.project.core.springSecurityFinalProject.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesMapper {

    List<RoleDTO> toRolesDTOList(List<Role> roles);
    Role toRoleEntity(RoleDTO roleDTO);
    RoleDTO toRoleDTO(Role role);
}
