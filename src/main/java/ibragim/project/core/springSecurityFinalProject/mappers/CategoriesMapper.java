package ibragim.project.core.springSecurityFinalProject.mappers;

import ibragim.project.core.springSecurityFinalProject.dto.CategoryDTO;
import ibragim.project.core.springSecurityFinalProject.models.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {

    List<CategoryDTO> toCategoriesDTOList(List<Category> categories);
    Category toCategoryEntity(CategoryDTO categoryDTO);
    CategoryDTO toCategoryDTO(Category category);
}
