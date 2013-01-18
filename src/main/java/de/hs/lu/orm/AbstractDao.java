package de.hs.lu.orm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public abstract class AbstractDao<T> {
	
    @PersistenceContext
    protected EntityManager entityManager;
    
    private Class<T> type;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractDao(){
    	
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.type = (Class) pt.getActualTypeArguments()[0];
    	
    }
    
    public void persist(T t) {
    	entityManager.persist(t);
    }

    public void remove(T t) {
        entityManager.remove(t);
    }

	public T merge(T t) {
        return entityManager.merge(t);
    }
	
	public T findById(Long id){
		return entityManager.find(type, id);
	}
	
	public void flush(){
		entityManager.flush();
		entityManager.clear();
	}


}
