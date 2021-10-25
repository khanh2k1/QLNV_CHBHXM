package com.tuandoan.dao;

import java.util.List;

import com.tuandoan.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//currentSession.beginTransaction();
		// create a query  ... sort by last name
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer",
											Customer.class);
		// execute query and get result list
		//System.out.println("ON getResultList()");
		List<Customer> ILoveYous = theQuery.getResultList();
		//currentSession.getTransaction().commit();
		//System.out.println("daoimpllllllllllllllllll");
		//System.out.println(ILoveYous);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// return the results		
		return ILoveYous;
	}

	@Override
	public void saveCustomer(Customer theACustomer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the customer ... finally LOL
		//System.out.println("START impl******************");
		//System.out.println(theACustomer);
		currentSession.saveOrUpdate(theACustomer);
		//System.out.println("SAVEDDDDDDDDDDDDDDDD");
		//System.out.println(theACustomer);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theACustomer = currentSession.get(Customer.class, theId);
		
		return theACustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}

	public void hibernate(){
		Session currentSession = sessionFactory.getCurrentSession();
		// create the objects
		/*
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

		/*Instructor tempInstructor =
				new Instructor("1", "THANH THUY", "thuy.com");
		Instructor instructor =
				new Instructor("3", "THANH THUY", "thuy.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail("2", "bong CHUYEN");
		InstructorDetail temp =
				new InstructorDetail("4", "fo");
		//InstructorDetail tempInstructorDetail = currentSession.get(InstructorDetail.class, 1);
		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		tempInstructorDetail.setInstructor(instructor);
		instructor.setInstructorDetail(temp);*/

		// save the instructor
		//
		// Note: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		//System.out.println("Saving instructor: " + tempInstructor);
		//Instructor instructor = currentSession.get(Instructor.class, 24);
		//instructor.setInstructorDetail(null);
		//System.out.println("*********** " + instructor);
		//System.out.println("instructorrrrrrrrrrr ->>>>>>>>>" + instructor);
		//currentSession.save(tempInstructor);
		//System.out.println(tempInstructor);
		/*currentSession.save(tempInstructor);
		System.out.println(tempInstructor);
		System.out.println(instructor);*/

		//System.out.println(tempInstructorDetail.getInstructor());
		//currentSession.delete(instructor);
		//currentSession.delete(tempInstructorDetail);

		/*Instructor tempInstructor =
				new Instructor("very new Susan", "Public", "susan.public@luv2code.com");*/
		/*Instructor tempInstructor = currentSession.get(Instructor.class, 12);
		// create some courses
		Course tempCourse1 = new Course("2 hahhhh Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("2 okkkkkkkkkk The Pinball Masterclass");*/

		// add courses to instructor
		/*tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);*/
		//tempCourse1.setInstructor(tempInstructor);

		/*currentSession.save(tempCourse1);
		System.out.println("************************");
		currentSession.save(tempCourse2);*/
		/*System.out.println(tempInstructor);
		currentSession.save(tempInstructor);*/
		/*System.out.println(tempInstructor);
		tempInstructor.setEmail("MESSI.@GMAIL.7BONGVANG");
		System.out.println(tempInstructor);*/
		/*
		System.out.println(tempCourse1);
		System.out.println(tempCourse2);*/


		/*Course tempCourse = currentSession.get(Course.class, 29);
		currentSession.delete(tempCourse);
		System.out.println("--------------");
		System.out.println(tempCourse);*/

		/*Instructor tempCourse = currentSession.get(Instructor.class, 9);
		currentSession.delete(tempCourse);
		System.out.println("--------------");
		System.out.println(tempCourse);*/


		//Course tempCourse = new Course("Pacman - How To Score One Million Points");
		//Course tempCourse = currentSession.get(Course.class, 12);
		// add some reviews
		/*tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done"));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));*/
		/*tempCourse.addReview(new Review("1 i'm so hungry"));
		tempCourse.addReview(new Review("1 222 i'm so hungry"));

		currentSession.delete(tempCourse);
		// save the course ... and leverage the cascade all :-)
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());*/

		/*currentSession.save(tempCourse);
		System.out.println("=================");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());*/


		// create a course
		//Course tempCourse = new Course("Pacman - How To Score One Million Points");
		Course tempCourse = currentSession.get(Course.class, 16);
		// save the course
		/*System.out.println("\nSaving the course ...");
		currentSession.save(tempCourse);
		System.out.println("Saved the course: " + tempCourse);

		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the students
		System.out.println("\nSaving students ...");
		currentSession.save(tempStudent1);
		currentSession.save(tempStudent2);*/
		currentSession.delete(tempCourse);
		System.out.println(tempCourse);
		System.out.println("Saved students: " + tempCourse.getStudents());

		/*Student student = currentSession.get(Student.class, 9);
		System.out.println(student);
		System.out.println(student.getCourses());*/

		System.out.println("Done!");
		//throw new IndexOutOfBoundsException("hahaha throw");
	}

}











