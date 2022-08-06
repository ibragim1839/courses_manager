package ibragim.project.core.springSecurityFinalProject.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class RoleDTO {
    private Long id;
    private String role;
}
