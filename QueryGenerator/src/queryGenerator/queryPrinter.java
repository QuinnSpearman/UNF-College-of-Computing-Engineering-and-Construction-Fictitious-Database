package queryGenerator;
import java.io.*;
import java.util.*;

public class queryPrinter {
	fileParser data = new fileParser();
	String nNumber, firstName, lastName, department, major, email, phoneNumber, year, advisorNNumber;
	float gpa;
	

	queryPrinter(){	
		
	final Formatter x;
		generateStudentTable();
		generateAdvisorTable();
		generateDepartmentTable();
		generateProfessorTable();
	}


	
	public void generateStudentTable() {

		
		System.out.println("INSERT INTO students(n_number, first_name, last_name, department, major, email, phone_number, grade, gpa, advisor_n_number) VALUES");
		
		for(int i = 1; i <= 500; i++) {
			nNumber = data.generateStudentNNumber();
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			major = data.generateMajor();
			department = data.generateDepartment(major);
			email = data.generateEmail(nNumber);
			phoneNumber = data.generatePersonalPhoneNumber();
			year = data.generateYear();
			advisorNNumber = data.chooseAdvisor(department);
			gpa = data.generateGPA();
			System.out.printf("('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%.2f', '%s')", nNumber, firstName, lastName, department, major, email, phoneNumber, year, gpa, advisorNNumber);
			if(i == 500) {
				System.out.print(";");
			}
			else {
				System.out.print(",");
			}
			System.out.print("\n");
			
		}
	}
	
	public void generateProfessorTable() {
		System.out.println("\n\nINSERT INTO professors(n_number, first_name, last_name, department, email, phone_number) VALUES");
		
		for(int i = 0; i < 17; i++){
			nNumber = data.generateFacultyNNumber();
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			department = "Computing";
			email = data.generateEmail(nNumber);
			phoneNumber = data.generatePersonalPhoneNumber();
			System.out.printf("('%s','%s','%s','%s','%s','%s'),\n", nNumber, firstName, lastName, department, email, phoneNumber);
		}
		
		for(int i = 1; i < 24; i++){
			nNumber = data.generateFacultyNNumber();
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			department = "Engineering";
			email = data.generateEmail(nNumber);
			phoneNumber = data.generatePersonalPhoneNumber();
			System.out.printf("('%s','%s','%s','%s','%s','%s'),\n", nNumber, firstName, lastName, department, email, phoneNumber);
		}
		for(int i = 1; i <= 5; i++){
			nNumber = data.generateFacultyNNumber();
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			department = "Construction";
			email = data.generateEmail(nNumber);
			phoneNumber = data.generatePersonalPhoneNumber();
			System.out.printf("('%s','%s','%s','%s','%s','%s')", nNumber, firstName, lastName, department, email, phoneNumber);
			if(i == 5) {
				System.out.print(";");
			}
			else {
				System.out.print(",");
			}
			System.out.print("\n");
		}
	}
	
	public void generateDepartmentTable() {
		System.out.println("\n\nINSERT INTO departments(dept_name, dept_head, dept_phone) VALUES");
		String[] departments = {"Computing", "Engineering", "Construction"};
		
		for(int i = 0; i < 3; i++) {
	 		firstName = data.generateFirstName();
			lastName = data.generateFirstName();
			phoneNumber = data.generateDeptPhoneNumber();
			System.out.printf("('%s','%s','%s')", departments[i], firstName + " " + lastName, phoneNumber);
			if(i == 2) {
				System.out.print(";");
			}
			else {
				System.out.print(",");
			}
			System.out.print("\n");
		}
	}
	
	public void generateAdvisorTable() {
		System.out.println("\n\nINSERT INTO advisors(n_number, first_name, last_name, email, department) VALUES");

		
		for(int i = 0; i < data.getAdvisorArrSize("Computing"); i++) {
			nNumber = data.getAdvisor("Computing", i);
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			department = "Computing";
			email = data.generateEmail(nNumber);
			
			System.out.printf("('%s','%s','%s','%s','%s'),\n", nNumber, firstName, lastName, email ,department);
		}
		
		for(int i = 0; i < data.getAdvisorArrSize("Engineering"); i++) {
			nNumber = data.getAdvisor("Engineering", i);
			firstName = data.generateFirstName();
			lastName = data.generateLastName();
			department = "Engineering";
			email = data.generateEmail(nNumber);
			
			System.out.printf("('%s','%s','%s','%s','%s'),\n", nNumber, firstName, lastName, email, department);
		}
		
		nNumber = data.getAdvisor("Construction", 0);
		firstName = data.generateFirstName();
		lastName = data.generateLastName();
		department = "Construction";
		email = data.generateEmail(nNumber);
		
		System.out.printf("('%s','%s','%s','%s','%s');\n", nNumber, firstName, lastName, email, department);
	}
	
	
}
