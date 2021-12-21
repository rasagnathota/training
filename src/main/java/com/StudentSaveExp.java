package com;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class StudentSaveExp {
	public static void main(String[] args) {
		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			emf = Persistence.createEntityManagerFactory("training");
			entityManager = emf.createEntityManager();
			
			transaction = entityManager.getTransaction();

			// start transaction
			transaction.begin();

			// entity
			StudentExp student = new StudentExp();
			student.setStudentName("Rasagna Thota");
			student.setRegno("103");
			student.setContactNo("1235678340");

			// save call
			entityManager.persist(student);
			
			transaction.commit();
			entityManager.close();
			
			System.out.println("Student details successfully saved");

		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		} finally {
			if (transaction.isActive()) {
				transaction.commit();
			}
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
}