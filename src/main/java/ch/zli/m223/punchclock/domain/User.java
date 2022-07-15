package ch.zli.m223.punchclock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;




@Entity
@NamedQuery(
    name="findUserByUsername",
    query="FROM User WHERE username = :username"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwort;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public void setUsername(String username){
        this.username = username;
   }
   public void setPasswort(String passwort) {
       this.passwort = passwort;
   }

   public String getUsername() {
       return username;
   }

   public String getPasswort() {
    return passwort;
}
}
