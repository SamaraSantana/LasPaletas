package br.senai.sp.paletas.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDao {
	protected EntityManager manager;
	
	public AbstractDao(){
		manager = ConnectionFactory.getManager();
	}		
	
	protected void commit(){
		try {
			manager.getTransaction().begin();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println(e.getMessage());
		}			
	}

}
