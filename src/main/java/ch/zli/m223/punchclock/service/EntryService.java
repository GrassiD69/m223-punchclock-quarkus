package ch.zli.m223.punchclock.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional 
    public Entry findEntry(long id) {
        Entry entry = entityManager.find(Entry.class, id);
        return entry;
    }


    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }


    @Transactional
    public void deleteEntry(long id){
        Entry entry = entityManager.find(Entry.class, id);
        entityManager.remove(entry);
    }

    @Transactional
    public void updateEntry(Entry entry){
        entityManager.merge(entry);
    }
}
