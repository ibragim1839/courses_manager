package ibragim.project.core.springSecurityFinalProject.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private int price;

    @Column
    private double rating;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToMany
    private List<Category> categories;

}
