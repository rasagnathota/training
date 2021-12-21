package com.onetomanyexp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class OneToManyCourse {

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
			Student student = new Student();
			student.setStudentName("Rasagna Thota");
			student.setRegno("101");
			student.setContactNo("1234567890");

			Course course1 = new Course();
			course1.setAmount(10000);
			course1.setStudent(student);

			Course course2 = new Course();
			course2.setAmount(10000);
			course2.setStudent(student);

			List<Course> courseList = new ArrayList();
			courseList.add(course1);
			courseList.add(course2);

			student.setListCourse(courseList);

			// save call
			entityManager.persist(student);
			System.out.println("Student details are successfully saved");

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
