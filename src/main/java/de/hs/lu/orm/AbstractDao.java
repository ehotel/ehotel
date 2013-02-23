package de.hs.lu.orm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
public abstract class AbstractDao<T> {
	
    @PersistenceContext
    protected EntityManager entityManager;
    
    private Class<T> type;
    
    public AbstractDao(Class<T> entityClass){
        this.type = entityClass;
    }
    
    @Transactional
    public void persist(T t) {
    	entityManager.persist(t);
    }

    @Transactional
    public void remove(T t) {
        entityManager.remove(t);
    }

    @Transactional
	public T merge(T t) {
        return entityManager.merge(t);
    }
    
    public T getReference(Long id) {
    	return entityManager.getReference(type, id);
    }    
    	
	public T findById(Long id){
		return entityManager.find(type, id);
	}
	
	@Transactional
	public void flush(){
		entityManager.flush();
		entityManager.clear();
	}
}
