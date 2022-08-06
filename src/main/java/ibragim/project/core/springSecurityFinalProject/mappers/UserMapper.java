package ibragim.project.core.springSecurityFinalProject.mappers;


import ibragim.project.core.springSecurityFinalProject.dto.UserDTO;
import ibragim.project.core.springSecurityFinalProject.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserDTO> getListOfUserDTOs(List<User> users);
    User DTOToUserEntity(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
