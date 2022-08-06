package ibragim.project.core.springSecurityFinalProject.repositories;


import ibragim.project.core.springSecurityFinalProject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Locale;

@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query(value = "DELETE FROM courses_categories WHERE categories_id=?", nativeQuery = true)
    public void deleteCoursesAndCategoriesLinksByCategoryId(@Param("?")Long id);
}
