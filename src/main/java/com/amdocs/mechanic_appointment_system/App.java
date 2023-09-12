package com.amdocs.mechanic_appointment_system;
import java.sql.SQLException;
import java.util.Scanner;

import exceptions.AppointmentNotFoundException;
import exceptions.SystemException;
import mechanic.dao.impl.MechanicDaoSqlImpl;
import mechanicdao.MechanicDao;
import pages.Customer;
import pages.Mechanic;
public class App 
{
	private static Scanner sc = new Scanner(System.in);
    public static void main( String[] args )
    {
    	try {
        	while(true) {
        		System.out.println("MAIN MENU");
        		System.out.println("\n ___________________________________________");
        		System.out.println("\n 1. Customer");
        		System.out.println("\n 2. Mechanic");
        		System.out.println("\n 3. Appointment");
        		System.out.println("\n 4. Service");
        		System.out.println("\n 0. Exit");
        		System.out.println("\n Enter your choice");
        		System.out.println("\n ___________________________________________");
        		int ch = Integer.parseInt(sc.nextLine());
        		switch (ch) {
        		case 1 : 
        			Customer.menu();
        			break;
        		case 2 :
        			Mechanic.menu();
        			break;
        		case 3 :
        			Appointment();
        			break;
        		case 4 :
        			Service();
        			break; 
        		case 0 :
        			System.exit(0);
        			default:
        				System.out.println("Try Again ");
        				System.out.println("choose in between 0 to 4");
        				break;
        		}
        	}
        }catch (SystemException e) {
        	System.out.println("Error : "+ e.getMessage());
        }
    	catch(NumberFormatException e) {
        	System.out.println("Number Format : " + e.getMessage());
        	App.main(null);
        } catch (Exception e) {
        	System.out.println("Error : "+ e.getMessage());
        }
    }
	private static void Appointment() throws SQLException,AppointmentNotFoundException {
		MechanicDao dao = new MechanicDaoSqlImpl();
		System.out.println("enter your appointment id to display your details");
		int sid = Integer.parseInt(sc.nextLine());
		dao.singleRecord(sid);
	}
	private static void Service() {
		// TODO Auto-generated method stub
		System.out.println("this option is not yet usable");
		main(null);
	}
}
