package ch.zli.m223.punchclock.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Project;

@ApplicationScoped
public class ProjectService {

    @Inject
    EntityManager entityManager;

    ProjectService(){}


    @Transactional 
    public void createCategory(Project project){
        entityManager.persist(project);
    }
}
