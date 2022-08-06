package ibragim.project.core.springSecurityFinalProject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private int price;
    private double rating;
    private List<CategoryDTO> categories;
    private UserDTO author;
}
