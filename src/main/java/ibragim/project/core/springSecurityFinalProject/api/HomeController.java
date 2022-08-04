package ibragim.project.core.springSecurityFinalProject.api;

import ibragim.project.core.springSecurityFinalProject.models.User;
import ibragim.project.core.springSecurityFinalProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @Autowired
    UserService userService;


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
        System.out.println("1");
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


    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            return user;
        }
        return null;
    }

}
