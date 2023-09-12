package pages;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amdocs.mechanic_appointment_system.App;

import exceptions.AppointmentNotFoundException;
import exceptions.IllegalArugementException;
import mechanic.dao.impl.MechanicDaoSqlImpl;
import mechanicdao.MechanicDao;
import entity.MechanicEntity;
import exceptions.SystemException;

public class Mechanic {
	private static Scanner sc = new Scanner(System.in);
	public static void menu() {
		
		try {
			while(true) {
				System.out.println("\n ___________________________________________");
				System.out.println("\n APPOINTMENT MENU");
				System.out.println("\n 1.Book an appointment");
				System.out.println("\n 2.Modify appointment details");
				System.out.println("\n 3.Delete an appointment");
				System.out.println("\n 4.View single record");
				System.out.println("\n 5.View all records");
				System.out.println("\n 6.go back ");
				System.out.println("\n 0.Exit");
				System.out.println("\n choose your option");
				System.out.println("\n ___________________________________________");
				int chs = Integer.parseInt(sc.nextLine());
		switch(chs) {
		case 1:
			bookAppointment();
			break;
		case 2:
			modifyCustomerDetails();
			break;
		case 3:
			deletecustomer();
			break;
		case 4:
			viewSingleRecord();
			break;
		case 5:
			viewAllRecords();
			break;
		case 6:
			App.main(null);
		case 0: 
			System.exit(0);
			default:
				System.out.println("Try again");
		}
	}
		}
			catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
	catch (Exception e) {

	System.out.println("Error : " + e.getMessage());
}
}
	private static MechanicDao dao = new MechanicDaoSqlImpl();
	
	private static void bookAppointment() throws SystemException, SQLException ,AppointmentNotFoundException, IllegalArugementException{
		// TODO Auto-generated method stub
		MechanicDaoSqlImpl ms = new MechanicDaoSqlImpl();
		MechanicEntity obj = new MechanicEntity();
		
		try {
			System.out.println("Enter The First Name: ");
			String firstName = sc.nextLine();
			if(firstName.isEmpty()) {
				throw new IllegalArugementException("First Name Cannot be Empty ");
			}
			obj.setFirstName(firstName );
			System.out.println("Enter The Last Name: ");
			String lastName = sc.nextLine();
			obj.setLastName(lastName);
			System.out.println("enter your phone number: ");
			String phoneNumber = checkPhoneNumber();
			System.out.println("enter your emailId: ");
			String emailId = sc.nextLine();
			obj.setEmailId(emailId);
			System.out.println("enter your city: ");
			String city = sc.nextLine();
			obj.setCity(city);
			System.out.println("Enter your desired mechanic Name: ");
			String mechanicName =sc.nextLine();
			obj.setMechanicName(mechanicName);
			System.out.println("enter your desired date: ");
			String appointmentDate = sc.nextLine();
			obj.setAppointmentDate(appointmentDate);
			System.out.println("Your details are: firstName= " + firstName + ", lastName= " + lastName +", phoneNumber= " + phoneNumber + ",EmailID= " + emailId + ", city= " + city+", MechanicName= "+mechanicName+",date of appointment is "+ appointmentDate );
		} catch (NumberFormatException e) {
			throw new SystemException("Please Provide a number value\n " + e.getMessage());
		}
		ms.bookappoint(obj);
		
	}
	private static String checkPhoneNumber() throws SystemException {
		System.out.println("enter your phone number: ");
		String phoneNumber = sc.nextLine();
		
		Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");  
		//the matcher() method creates a matcher that will match the given input against this pattern  
		Matcher match = ptrn.matcher(phoneNumber);  
		//returns a boolean value  
		if(match.find() && match.group().equals(phoneNumber)){
			return phoneNumber;
		}else {
			throw new SystemException("Invalid Phone Number");
		}
	}
	private static void modifyCustomerDetails() throws SQLException,AppointmentNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter id to get update your mechanic details");
		int moid=Integer.parseInt(sc.nextLine());
		dao.singleRecord(moid);
		dao.modify(moid);
	}
	
	private static void deletecustomer() throws SQLException,AppointmentNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("enter id to be deleted");
		int dId = Integer.parseInt(sc.nextLine());
		
		dao.delete(dId);
	}

	private static boolean viewSingleRecord() throws SQLException, AppointmentNotFoundException {
		boolean vs = false;
	try {
		
	
		System.out.println("enter id to be displayed");
		int sid = Integer.parseInt(sc.nextLine());
		System.out.println("printing your selected id details");
		vs = dao.singleRecord(sid);
	
	} catch (SQLException e) {
		System.err.println(e);
	}
	return vs;
	}

	private static void viewAllRecords() throws SQLException,AppointmentNotFoundException   {
		try {
		List<MechanicEntity> allAppointments = dao.selectAll();
	for (MechanicEntity appoint : allAppointments) {
		if(appoint != null)
			System.out.println("printing all appointment details");
			System.out.println(appoint);
	}
		}
		
		
		catch(SQLException e) {
			System.err.println(e);
		}
	}
	}	

