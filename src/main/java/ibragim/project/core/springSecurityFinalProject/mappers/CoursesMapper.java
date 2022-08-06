package ibragim.project.core.springSecurityFinalProject.mappers;

import ibragim.project.core.springSecurityFinalProject.dto.CourseDTO;
import ibragim.project.core.springSecurityFinalProject.dto.RoleDTO;
import ibragim.project.core.springSecurityFinalProject.models.Course;
import ibragim.project.core.springSecurityFinalProject.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursesMapper {

    List<CourseDTO> toCoursesDTOList(List<Course> courses);
    Role toCourseEntity(CourseDTO courseDTO);
    CourseDTO toCourseDTO(Course course);
}
