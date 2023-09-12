package pages;
import exceptions.CustomerNotFoundException;
import exceptions.IllegalArugementException;
import exceptions.SystemException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import java.util.regex.Pattern;

import com.amdocs.mechanic_appointment_system.App;

import customer.dao.impl.CustomerDaoSqlImpl;
import customerdao.CustomerDao;
import entity.CustomerEntity;
public class Customer {
	private static Scanner sc = new Scanner(System.in);
	public static void menu() {
		
		try {
			while(true) {
				System.out.println("\n CUSTOMER MENU");
				System.out.println("\n ___________________________________________");
				System.out.println("\n 1.Register Customer");
				System.out.println("\n 2.Modify Customer details");
				System.out.println("\n 3.Delete Customer record");
				System.out.println("\n 4.View single record");
				System.out.println("\n 5.View all records");
				System.out.println("\n 6.go back ");
				System.out.println("\n 0.Exit");
				System.out.println("\n choose your option");
				System.out.println("\n ___________________________________________");
		int chs = Integer.parseInt(sc.nextLine());
		switch(chs) {
		case 1:
			registerCustomer();
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
}catch(IllegalArugementException e) {
	System.out.println(e.getMessage());
}
		catch (NumberFormatException e) {
	System.out.println("Please enter range of integers from 0 to 6 : " + e.getMessage());
} catch (Exception e) {

	System.out.println("Error : " + e.getMessage());
}
}

	private static CustomerDao dao = new CustomerDaoSqlImpl();
	
	private static void registerCustomer() throws SystemException, SQLException, IllegalArugementException{
		// TODO Auto-generated method stub
			CustomerDaoSqlImpl cs = new CustomerDaoSqlImpl();
			CustomerEntity obj = new CustomerEntity();
		
			System.out.println("Enter The First Name: ");
			String firstName = sc.nextLine();
			if(firstName.isEmpty()) {
				throw new IllegalArugementException("First Name Cannot be Empty ");
			}
			obj.setFirstName(firstName );
			System.out.println("Enter The Last Name: ");
			String lastName = sc.nextLine();
			obj.setLastName(lastName);
			System.out.println("Enter Your Age: ");
			int age =Integer.parseInt(sc.nextLine());
			obj.setAge(age);
			System.out.println("enter your phone number: ");
			String phoneNumber = checkPhoneNumber();
			System.out.println("enter your address: ");
			String address = sc.nextLine();
			obj.setAddress(address);
			System.out.println("Your details are:\n firstName= " + firstName + ",\n lastName= " + lastName + ", \n Age= " + age +", \n phoneNumber= " + phoneNumber + ", \n Address= " + address );
		
		cs.register(obj);
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
	private static void modifyCustomerDetails() throws SQLException,CustomerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Enter id to get update your details");
		int mid=Integer.parseInt(sc.nextLine());
		dao.singleRecord(mid);
		dao.modify(mid);
	}
	
	private static void deletecustomer() throws SQLException,CustomerNotFoundException{
		System.out.println("enter your id to delete your record");
			int dId = Integer.parseInt(sc.nextLine()); 
			System.out.println("printing your selected id details");
			dao.delete(dId);
		}
	private static boolean viewSingleRecord() throws SQLException,CustomerNotFoundException {
		// TODO Auto-generated method stub
		boolean cs = false;
		try {
		System.out.println("enter id to be displayed");
		int vid = Integer.parseInt(sc.nextLine());
		System.out.println("printing your selected id details");
		cs =dao.singleRecord(vid);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return cs;
	}
	
	private static void viewAllRecords() throws SQLException {
		// TODO Auto-generated method stub
		try {
		List<CustomerEntity> allCustomers = dao.selectAll();
		for (CustomerEntity customer : allCustomers) {
			if(customer != null)
				System.out.println("printing all customers");
				System.out.println(customer);
		}
		}catch(SQLException e) {
    		System.err.println(e);
    	}
	}
	
	

	
	
	

	
}

