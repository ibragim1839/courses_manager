package ibragim.project.core.springSecurityFinalProject.models;

import jdk.jfr.Name;
import jdk.jfr.StackTrace;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
