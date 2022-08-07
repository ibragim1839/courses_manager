package ibragim.project.core.springSecurityFinalProject.rest;

import ibragim.project.core.springSecurityFinalProject.dto.CourseDTO;
import ibragim.project.core.springSecurityFinalProject.mappers.CoursesMapper;
import ibragim.project.core.springSecurityFinalProject.models.Course;
import ibragim.project.core.springSecurityFinalProject.services.CoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/courses")
@CrossOrigin
@RequiredArgsConstructor
public class FrontController {

    private final CoursesService coursesService;
    private final CoursesMapper coursesMapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> courseDTOList = coursesMapper.toCoursesDTOList(coursesService.getAllCourses());
        return new ResponseEntity<>(courseDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{courseId}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable(name = "courseId") Long id){
        return new ResponseEntity<>(coursesMapper.toCourseDTO(coursesService.getCourseById(id)), HttpStatus.OK);
    }
}
