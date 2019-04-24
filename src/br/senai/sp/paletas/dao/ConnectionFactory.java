package br.senai.sp.paletas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	static {
		factory = Persistence.createEntityManagerFactory("grupo8");
	}
	
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	
	public static EntityManager getManager(){
		if (manager == null) {
			manager = factory.createEntityManager();
		}
		return manager;
	}
	
	public static void close(){
		if (manager != null) {
			manager.close();
			factory.close();	
		}
		
	}
}
