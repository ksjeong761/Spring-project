package com.devistat.server.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//https://www.baeldung.com/spring-dao-jpa
public abstract class AbstractRepository< T extends Object > {
	
	   private Class< T > clazz;

	   @PersistenceContext
	   EntityManager entityManager;

	   public final void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }

	   public T findOne( long id ){
	      return entityManager.find( clazz, id );
	   }
	   
	   public List< T > findAll(){
	      return entityManager.createQuery( "from " + clazz.getName() )
	       .getResultList();
	   }

	   public void create( T entity ){
	      entityManager.persist( entity );
	   }

	   public T update( T entity ){
	      return entityManager.merge( entity );
	   }

	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   
	   public void deleteById( long entityId ){
	      T entity = findOne( entityId );
	      delete( entity );
	   }
	}