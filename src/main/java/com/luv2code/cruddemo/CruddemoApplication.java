package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO, "Kamil");

			// updateStudent(studentDAO, 3);
			
			// removeStudent(studentDAO, 3);

			// removeAllStudents(studentDAO);
		};
	}

	private void removeAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students...");
		int numRowsDeleted = studentDAO.deleteAllStudents();

		System.out.println("Deleted row count: " + numRowsDeleted);

	}

	private void removeStudent(StudentDAO studentDAO, Integer id) {
		System.out.println("Deleting the student with id: " + id);

		studentDAO.deleteStudent(id);
	}

	private void updateStudent(StudentDAO studentDAO, int studentId){
		System.out.println("Getting student with the id: "+ studentId);

		Student myStudent = studentDAO.findByID(studentId);

		System.out.println("Updating student...");

		myStudent.setFirstName("Selda");
		studentDAO.update(myStudent);

		System.out.println("Updated student: " + myStudent);

	}

	private void queryForStudentsByLastName(StudentDAO studentDAO, String lastName) {
		List<Student> studentList = studentDAO.findByLastName(lastName);

		for (Student theStudent: studentList) {
			System.out.println(theStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		List<Student> studentList = studentDAO.findAll();

		for(Student student : studentList){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		// create student object
		System.out.println("Creating the student...");
		Student tempStudent = new Student("Peter", "Parker", "peter@luv2code.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int id = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + id);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id" + id);
		Student myStudent = studentDAO.findByID(id);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create students
		System.out.println("Creating three students...");
		Student tempStudent1 = new Student("Kim", "Petras","kim@luv2code.com");
		Student tempStudent2 = new Student("Emircan", "Igrek", "emircan@luv2code.com");
		Student tempStudent3 = new Student("Selda", "Bagcan", "selda@luv2code.com");

		// save those students
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Student is saved. Generated id: " + tempStudent.getId());
	}
}
