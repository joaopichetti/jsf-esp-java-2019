package br.edu.utfpr.jsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.edu.utfpr.jsf.util.JPAUtil;

public class DAO<T> {

	private final Class<T> modelClass;

	public DAO(Class<T> modelClass) {
		this.modelClass = modelClass;
	}
	
	public void delete(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		try {
			em.remove(em.merge(t));
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();	
		}		
	}
	
	public List<T> findAll() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> lista = null;
		try {
			CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(modelClass);
			query.select(query.from(modelClass));
			lista = em.createQuery(query).getResultList();
		} finally {
			em.close();
		}
		return lista;
	}

	public T findById(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = null;
		try {
			instancia = em.find(modelClass, id);
		} finally {
			em.close();
		}
		return instancia;
	}	
	
	public void insert(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(t);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();	
		}
	}

	public void update(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(t);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

}
