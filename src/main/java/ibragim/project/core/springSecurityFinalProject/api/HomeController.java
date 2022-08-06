package ibragim.project.core.springSecurityFinalProject.api;

import ibragim.project.core.springSecurityFinalProject.models.Category;
import ibragim.project.core.springSecurityFinalProject.models.Course;
import ibragim.project.core.springSecurityFinalProject.models.User;
import ibragim.project.core.springSecurityFinalProject.services.CategoriesService;
import ibragim.project.core.springSecurityFinalProject.services.CoursesService;
import ibragim.project.core.springSecurityFinalProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @Autowired
    private CategoriesService categoriesService;

    private final CoursesService coursesService;



    @GetMapping(value = "/")
    public String getMainPage(Model model){
        return "mainPage";
    }


    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/enter")
    public String getLoginPage(Model model){
        return "enterPage";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String getProfilePage(Model model){
        model.addAttribute("user", getCurrentUser());
        return "profilePage";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value="/registration")
    public String getRegistrationPage(){
        return "registrationPage";
    }


    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/userRegistration")
    public String registerNewUser(@RequestParam(name = "user_fullName") String fullName,
                                  @RequestParam(name = "user_email") String email,
                                  @RequestParam(name = "user_password") String password,
                                  @RequestParam(name = "user_password1") String password1){
        if(fullName!=null && email!=null && password!=null && password1!=null){
            if(password.equals(password1)){
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setFullName(fullName);
                userService.registerNewUser(newUser);
                return "redirect:/enter?registrationSuccess";
            }
            else{ return "redirect:/registration?passwordError";}
        }
        else{
            return "redirect:/registration?requestError";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/categories")
    public String getCategoriesPage(Model model){
        model.addAttribute("categories", categoriesService.getCategories());
        return "/categoriesPage";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/addNewCategory")
    public String addNewCategory(@RequestParam(name = "categoryName")String name, @RequestParam(name = "image")String image){
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setImage(image);
        categoriesService.addNewCategory(newCategory);
        return "redirect:/categories";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/category/{id}")
    public String getCategory(@PathVariable(name = "id") Long categoryId, Model model){
        if (categoriesService.getCategoryById(categoryId)!=null){
            model.addAttribute("category",categoriesService.getCategoryById(categoryId));
            model.addAttribute("courses", coursesService.getCoursesByCategoryId(categoryId));
            model.addAttribute("authors", userService.getUsers());
            return "coursesPage";
        }
        return "redirect:/unknown";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/addNewCourse")
    public String addNewCourse(@RequestParam(name = "courseName") String name,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "rating") Double rating,
                               @RequestParam(name = "price") int price,
                               @RequestParam(name = "authorId") Long authorId,
                               @RequestParam(name = "categoryId") Long categoryId){
        Course c = new Course();
        c.setName(name);
        c.setDescription(description);
        c.setRating(rating);
        c.setPrice(price);
        c.setAuthor(userService.getUserById(authorId));
        List<Category> cats = new ArrayList<>();
        cats.add(categoriesService.getCategoryById(categoryId));
        c.setCategories(cats);
        coursesService.addNewCourse(c);
        return "redirect:/category/"+categoryId;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/course/{id}")
    public String getCourse(@PathVariable(name = "id") Long courseId, Model model){
        if(coursesService.getCourseById(courseId)!=null){
            model.addAttribute("course", coursesService.getCourseById(courseId));
            model.addAttribute("categories", categoriesService.getCategories());
            model.addAttribute("authors", userService.getUsers());
            return "courseDetailsPage";
        }
        return "redirect:/unknown";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam(name = "categoryId") Long id){
        if (coursesService.checkIfThereAreCoursesInTheCategory(id)){
            categoriesService.deleteCoursesAndCategoriesLinksByCategoryId(id);
        }
        categoriesService.deleteCategoryById(id);
        return "redirect:/categories";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value ="/changeCategory")
    public String changeCategory(@RequestParam(name = "categoryName") String name,
                                  @RequestParam(name = "image") String image,
                                  @RequestParam(name = "categoryId") Long categoryId){
        Category category = categoriesService.getCategoryById(categoryId);
        if(category!=null){
            category.setName(name);
            category.setImage(image);
            categoriesService.addNewCategory(category);
            return "redirect:/category/"+category.getId();
        }
        return "redirect:/error";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/changeTheCourse")
    public String changeTheCourse(@RequestParam(name = "courseName") String name,
                                  @RequestParam(name = "description") String description,
                                  @RequestParam(name = "rating") Double rating,
                                  @RequestParam(name = "price") int price,
                                  @RequestParam(name = "authorId") Long authorId,
                                  @RequestParam(name = "courseId") Long id){
        Course c = coursesService.getCourseById(id);
        c.setName(name);
        c.setDescription(description);
        c.setRating(rating);
        c.setPrice(price);
        c.setAuthor(userService.getUserById(authorId));
        coursesService.addNewCourse(c);
        return "redirect:/course/"+id;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/deleteCourse")
    public String deleteCourse(@RequestParam(name = "courseId") Long id){
        coursesService.deleteCourseById(id);
        return "redirect:/categories";
    }

    @GetMapping(value = "/unknown")
    public String getErrorPage(){
        return "errorPage";
    }



    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

}
