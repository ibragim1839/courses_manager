package ibragim.project.core.springSecurityFinalProject.repositories;


import ibragim.project.core.springSecurityFinalProject.models.Category;
import ibragim.project.core.springSecurityFinalProject.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CoursesRepository extends JpaRepository<Course, Long>{

    @Query(value = "SELECT course_id FROM courses_categories WHERE categories_id=?", nativeQuery = true)
    List<Long> getCoursesIdByCategoryId(@Param("?")Long id);

    @Query(value = "SELECT COUNT(course_id) FROM courses_categories WHERE categories_id=?", nativeQuery = true)
    int checkIfThereAreCoursesInTheCategory(@Param("?") Long id);

}
