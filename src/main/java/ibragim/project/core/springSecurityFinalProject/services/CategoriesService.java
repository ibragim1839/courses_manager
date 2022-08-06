package ibragim.project.core.springSecurityFinalProject.services;


import ibragim.project.core.springSecurityFinalProject.dto.CategoryDTO;
import ibragim.project.core.springSecurityFinalProject.mappers.CategoriesMapper;
import ibragim.project.core.springSecurityFinalProject.models.Category;
import ibragim.project.core.springSecurityFinalProject.repositories.CategoriesRepository;
import ibragim.project.core.springSecurityFinalProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private  final CategoriesMapper categoriesMapper;

    public List<Category> getCategories(){
        return categoriesRepository.findAll();
    }

    public Category addNewCategory(Category category){
        return categoriesRepository.save(category);
    }

    public Category getCategoryById(Long id){
        return categoriesRepository.findById(id).orElse(null);
    }

    public void deleteCoursesAndCategoriesLinksByCategoryId(Long id){
        if(id!=null){
            categoriesRepository.deleteCoursesAndCategoriesLinksByCategoryId(id);
        }
    }

    public void deleteCategoryById(Long id){
        if(id!=null){
            categoriesRepository.deleteById(id);
        }
    }
}
