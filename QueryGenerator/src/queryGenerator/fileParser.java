package queryGenerator;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
//class names should be capitalized
public class fileParser {

	private ArrayList<String> firstNameArr = new ArrayList<String>();
	private ArrayList<String> lastNameArr = new ArrayList<String>();
	public ArrayList<String> cities = new ArrayList<String>();
	public ArrayList<String> countries = new ArrayList<String>();
	public ArrayList<String> states = new ArrayList<String>();
	public ArrayList<String> zipCodes = new ArrayList<String>();
	private ArrayList<String> nNumberList = new ArrayList<String>();
	private ArrayList<String> computingProfessors = new ArrayList<String>(); //17
	private ArrayList<String> engineeringProfessors = new ArrayList<String>(); //24
	private ArrayList<String> constructionProfessors = new ArrayList<String>(); //5
	private ArrayList<String> computingAdvisors = new ArrayList<String>(); //2
	private ArrayList<String> engineeringAdvisors = new ArrayList<String>(); //4
	private String constructionAdvisor;

	private Scanner x;
	BufferedReader br;

	Random rand = new Random();

	fileParser() {
		openFirstNameFile();
		readFirstNameFile();
		closeFile();
		openLastNameFile();
		readLastNameFile();
		closeFile();
		setAdvisors();
	}

	public void openFirstNameFile() {
		try {
			x = new Scanner(new File(
					"C:\\Users\\Quinn\\eclipse-workspace\\QueryGenerator\\src\\queryGenerator\\firstNames.txt"));
		} catch (Exception e) {
			System.out.println("File not Found.");
		}
	}

	public void openLastNameFile() {
		try {
			x = new Scanner(new File(
					"C:\\Users\\Quinn\\eclipse-workspace\\QueryGenerator\\src\\queryGenerator\\lastNames.txt"));
		} catch (Exception e) {
			System.out.println("File not Found.");
		}
	}

	public void readFirstNameFile() {
		while (x.hasNext()) {
			firstNameArr.add(x.next());
		}
	}

	public void readLastNameFile() {
		String tempStr;

		while (x.hasNext()) {
			tempStr = x.next();
			tempStr = tempStr.toLowerCase();
			tempStr = tempStr.replaceFirst(Character.toString(tempStr.charAt(0)),
					Character.toString(tempStr.charAt(0) - 32));
			lastNameArr.add(tempStr);
		}
	}

	public void displayFirstNames() {
		for (String name : firstNameArr) {
			System.out.println(name);
		}
	}

	public void displayLastNames() {
		for (String name : lastNameArr) {
			System.out.println(name);
		}
	}
	
	private void setAdvisors() {
		for(int i = 0; i < 2; i++) {
			computingAdvisors.add(generateFacultyNNumber());
		}
		
		for(int i = 0; i < 4; i++) {
			engineeringAdvisors.add(generateFacultyNNumber());
		}
		
		constructionAdvisor = generateFacultyNNumber();
		
	}

	
	public String chooseAdvisor(String department) {
		switch(department) {
		case "Computing":
			return computingAdvisors.get(rand.nextInt(computingAdvisors.size()));
		case "Engineering":
			return engineeringAdvisors.get(rand.nextInt(engineeringAdvisors.size()));
		default:
			return constructionAdvisor;
		}
	}
	
	public int getAdvisorArrSize(String dept) {
		if(dept == "Computing") {
			return computingAdvisors.size();
		}
		else if(dept == "Engineering"){
			return engineeringAdvisors.size();
		}
		return 1;
	}
	
	public int getProfessorArrSize(String dept) {
		if(dept == "Computing") {
			return computingProfessors.size();
		}
		else if(dept == "Engineering"){
			return engineeringProfessors.size();
		}
		return constructionProfessors.size();
	}
	
	
	public String getAdvisor(String dept, int index){
		if(dept == "Computing") {
			return computingAdvisors.get(index);
		}
		else if(dept == "Engineering"){
			return engineeringAdvisors.get(index);
		}
		return constructionAdvisor;
	}


	public void closeFile() {
		x.close();
	}

	public String generateFirstName() {
		return firstNameArr.get(rand.nextInt(firstNameArr.size()));
	}

	public String generateLastName() {
		return lastNameArr.get(rand.nextInt(lastNameArr.size()));
	}
	
	

	private String generatePhoneNumber(char mode) {
		int prob;
		int areaCode;
		if(mode == 'd') {
			areaCode = 904;
		}
		else {
			prob = rand.nextInt(4);
			switch(prob){
			case 0: 
			case 1:
			case 2:
				areaCode = 904;
				break;
			default:
				areaCode = rand.nextInt(900) + 100;
				break;
		}
			
		}
		int mid = rand.nextInt(900) + 101;
		int end = rand.nextInt(9000) + 1001;
		return areaCode + "-" + mid + "-" + end;
	}
	
	public String generateDeptPhoneNumber() {
		return generatePhoneNumber('d');
	}
	
	public String generatePersonalPhoneNumber() {
		return generatePhoneNumber('p');
	}
	
	private String generateNNumber(int range, int offset) {
		int nNumber;
		do {
			nNumber = rand.nextInt(range) + offset;
		}while(nNumberList.contains(Integer.toString(nNumber)));
		
		nNumberList.add(Integer.toString(nNumber));
		
		/*if(position == 'p') {
			switch(dept) {
			case "Computing":
				computingProfessors.add(nNumber);
			case "Engineering":
				engineeringProfessors.add(nNumber);
			case "Construction":
				constructionProfessors.add(nNumber);
			}
		}
		else if(position == 'a') {
			switch(dept) {
			case "Computing":
				computingAdvisors.add(nNumber);
			case "Engineering":
				engineeringAdvisors.add(nNumber);
			case "Construction":
				constructionAdvisor = nNumber;
			}
		}*/
		
		
		return "n0" + nNumber;
	}
	
	
	
	public String generateStudentNNumber() {
		return generateNNumber(200001, 1300000);
	}
	public String generateFacultyNNumber() {
		return generateNNumber(500001, 1000000);
	}

	
	public String generateEmail(String nNumber) {
		return nNumber + "@unf.edu";
	}
	
	
	public String generateYear() {
		int year = rand.nextInt(4);
		
		switch(year) {
		case 0:
			return "Freshman";
		case 1:
			return "Sophomore";
		case 2:
			return "Junior";
		default:
			return "Senior";
		}
	}
	
	public String generateDepartment(String major) {
		switch(major) {
		case "Computer Science":
		case "Information Systems":
		case "Information Science":
		case "Information Technology":
		case "Data Science":
			return "Computing";
		case "Civil Engineering":
		case "Electrical Engineering":
		case "Mechanical Engineering":
			return "Engineering";
		default:
			return "Construction";
			
		}
	}
	
	public String generateMajor() {
		int prob = rand.nextInt(9);
		
		switch(prob) {
		case 0:
			return "Computer Science";
		case 1:
			return "Information Science";
		case 2:
			return "Information Systems"; 
		case 3:
			return "Information Technology"; 
		case 4:
			return "Data Science";
		case 5:
			return "Civil Engineering";
		case 6:
			return "Electrical Engineering";
		case 7:
			return "Mechanical Engineering";
		default:
			return "Building Construction";
		}
	}
	
	public float generateGPA() {
		float gpa = (rand.nextInt(2) + 2) + rand.nextFloat();
		
		if(gpa > 3.99) {
			gpa = 4;
		}
		return gpa;
	}

}
