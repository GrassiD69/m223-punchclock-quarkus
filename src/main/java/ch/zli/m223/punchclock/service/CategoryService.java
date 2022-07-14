package ch.zli.m223.punchclock.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ch.zli.m223.punchclock.domain.Category;






public class CategoryService {
    @Inject
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category");
        return query.getResultList();
    }
    
}
