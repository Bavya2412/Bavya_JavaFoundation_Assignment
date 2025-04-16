package com.hexaware.sis.entity;

	import java.util.Date;
	import java.util.List;

	import com.hexaware.sis.exception.*;

	public class sis {

	    
	    public void addEnrollment(Student student, Course course, Date enrollmentDate)
	            throws DuplicateEnrollmentException, CourseNotFoundException, InvalidEnrollmentDataException {
	        student.enrollInCourse(course, enrollmentDate);
	        System.out.println("Enrollment successful.");
	    }

	    
	    

	    
	    public void addPayment(Student student, double amount, Date paymentDate) throws PaymentValidationException {
	        Payment payment = student.makePayment(amount, paymentDate);
	        System.out.println("Payment successful. Payment ID: " + payment.getPaymentId());
	    }

	    
	    public List<Enrollment> getEnrollmentsForStudent(Student student) {
	        return student.getEnrollments();
	    }

	    
	    public List<Course> getCoursesForTeacher(Teacher teacher) {
	        return teacher.getAssignedCourses();
	    }
	}


