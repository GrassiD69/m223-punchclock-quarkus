package ch.zli.m223.punchclock.domain;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="project")
    private Set<Entry> entries;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    
}
    

