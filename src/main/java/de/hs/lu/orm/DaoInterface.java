package de.hs.lu.orm;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public interface DaoInterface<T> {
	    
    void persist(T t);

    void remove(T t);

	T merge(T t);
    
    T getReference(Long id);
    
    T findById(Long id);
	
	void flush();

}
