package ibragim.project.core.springSecurityFinalProject.services;


import ibragim.project.core.springSecurityFinalProject.dto.CourseDTO;
import ibragim.project.core.springSecurityFinalProject.mappers.CoursesMapper;
import ibragim.project.core.springSecurityFinalProject.models.Category;
import ibragim.project.core.springSecurityFinalProject.models.Course;
import ibragim.project.core.springSecurityFinalProject.repositories.CategoriesRepository;
import ibragim.project.core.springSecurityFinalProject.repositories.CoursesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoursesService {

    private final CoursesRepository coursesRepository;
    private final CategoriesRepository categoriesRepository;

    public List<Course> getCoursesByCategoryId(Long id){
        Category category = categoriesRepository.findById(id).orElse(null);
        if(category!=null){
            List<Long> coursesId = coursesRepository.getCoursesIdByCategoryId(category.getId());
            List<Course> courses = new ArrayList<>();
            for(Long i: coursesId){
                courses.add(coursesRepository.findById(i).orElse(null));
            }
            return courses;
        }
        return null;
    }

    public Course addNewCourse(Course course){
        if(course!=null){
            return coursesRepository.save(course);
        }
        return null;
    }

    public Course getCourseById(Long id){
        return coursesRepository.findById(id).orElse(null);
    }

    public void deleteCourseById(Long id){
        if(id!=null){
            coursesRepository.deleteById(id);
        }
    }

    public boolean checkIfThereAreCoursesInTheCategory(Long id){
        if(coursesRepository.checkIfThereAreCoursesInTheCategory(id)>0){
            return true;
        }
        else{
            return false;
        }
    }
}
