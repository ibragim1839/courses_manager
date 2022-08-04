package ibragim.project.core.springSecurityFinalProject.services;

import ibragim.project.core.springSecurityFinalProject.models.Role;
import ibragim.project.core.springSecurityFinalProject.models.User;
import ibragim.project.core.springSecurityFinalProject.repositories.RolesRepository;
import ibragim.project.core.springSecurityFinalProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if(user!=null){
            return user;
        }
        else{
            throw new UsernameNotFoundException("USER IS NOT FOUND");
        }
    }

    public User registerNewUser(User user){
        List<Role> roles = new ArrayList<>();
        Role role = rolesRepository.findRoleByRole("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
