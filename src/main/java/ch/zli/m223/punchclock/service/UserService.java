package ch.zli.m223.punchclock.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.User;

@ApplicationScoped
public class UserService {

    @Inject
    private EntityManager entityManager;

    public UserService() {
    }

    @Transactional 
    public Optional<User> findUser(String username) {
        return entityManager.createNamedQuery("findUserByUsername", User.class)
            .setParameter("username", username)
            .getResultStream()
            .findFirst();
    }


    @Transactional 
    public void createUser(User user) {
        entityManager.persist(user);
    }


    
}

