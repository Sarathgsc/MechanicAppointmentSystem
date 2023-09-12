package mechanic.dao.impl;
import java.sql.Connection;
import exceptions.AppointmentNotFoundException;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbutil.Dbutil;
import mechanicdao.MechanicDao;
import pages.Mechanic;
import entity.MechanicEntity;

public class MechanicDaoSqlImpl implements MechanicDao {
	private static final String INSERT_APPOINT="insert into appointments(first_name,last_name,phone_number,emailId,city,mechanicName,appo_date )values(?,?,?,?,?,?,?)";
	private static final String SELECT_APPOINT="select * from appointments";
	private static final String DELETE_CUSTOMER="DELETE FROM appointments WHERE mid=?";
	private static final String SINGLE_RECORD="select * from appointments WHERE mid=?";
	private static String UPDATE_APPOINTMENT = null;
	private static Scanner sc = new Scanner(System.in);
	private  Connection connection=Dbutil.getConnection(); 
	
	public boolean bookappoint(MechanicEntity msEntity) throws SQLException,AppointmentNotFoundException {
		PreparedStatement ps = connection.prepareStatement(INSERT_APPOINT);
		ps.setString(1, msEntity.getFirstName());
		ps.setString(2, msEntity.getLastName());
		ps.setString(3, msEntity.getPhoneNumber());
		ps.setString(4, msEntity.getEmailId());
		ps.setString(5, msEntity.getCity());
		ps.setString(6, msEntity.getMechanicName());
		ps.setString(7, msEntity.getAppointmentDate());
		int excecuteUpdate = ps.executeUpdate();
		ps.close();
		if(excecuteUpdate>0) {
			return true;
		}
		return false;
	}
	public void modify(int id) throws SQLException, AppointmentNotFoundException {
		while(true) {
			System.out.println("Choose appointment update details");
			System.out.println("\n ___________________________________________");
			System.out.println("\n 1.PhoneNumber");
			System.out.println("\n 2.emailid");
			System.out.println("\n 3.City");
			System.out.println("\n 4.Date");
			System.out.println("\n 5.Mechanic Name");
			System.out.println("\n 0. Go back to main menu");
			System.out.println("\n ___________________________________________");
			int uid=Integer.parseInt(sc.nextLine());
			switch(uid) {
			case 1:
			System.out.println("enter new phone number");
			String newPhoneNumber = sc.nextLine();
			UPDATE_APPOINTMENT="update appointments set phone_number=? where mid=?";
			PreparedStatement ps = connection.prepareStatement(UPDATE_APPOINTMENT);
			ps.setString(1,newPhoneNumber);
			ps.setInt(2,id);
			ps.executeUpdate();
			System.out.println("new updated details are ");
			singleRecord(id);
			break;
			case 2:
				System.out.println("Enter emailId");
				String newemailId = sc.nextLine();
				PreparedStatement ps1 = connection.prepareStatement("update appointments set emailId=? where mid=?");
				ps1.setString(1,newemailId);
				ps1.setInt(2,id);
				ps1.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 3:
				System.out.println("Enter new city");
				String newcity = sc.nextLine();
				PreparedStatement ps2 = connection.prepareStatement("update appointments set city=? where mid=?");
				ps2.setString(1,newcity);
				ps2.setInt(2,id);
				ps2.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 4:
				
				System.out.println("Enter new Date for your appointment");
				String newdate  = sc.nextLine();
				PreparedStatement ps3 = connection.prepareStatement("update appointments set appo_date=? where mid=?");
				ps3.setString(1,newdate);
				ps3.setInt(2,id);
				ps3.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 5:
				System.out.println("Enter new Mechanic Id");
				String newmechanicId  = sc.nextLine();
				PreparedStatement ps4 = connection.prepareStatement("update appointments set mechanicName=? where mid=?");
				ps4.setString(1,newmechanicId);
				ps4.setInt(2,id);
				ps4.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 0:
				Mechanic.menu();
				default:
					System.out.println("Try again");
			}
		}
	}
	public boolean delete(int id) throws SQLException,AppointmentNotFoundException{
		PreparedStatement ps = connection.prepareStatement(DELETE_CUSTOMER);
		ps.setInt(1,id);
		int executeUpdate = ps.executeUpdate();
		System.out.println("Rows effected "+ executeUpdate);
		System.out.println("DELETE COMPLETE");
				ps.close();
		if(executeUpdate>0) {
			return true;
		}
		throw new AppointmentNotFoundException("INFO OF GIVEN "+id+" ID IS NOT FOUND TO DELETE");
	}
	
	public boolean singleRecord(int id) throws SQLException,AppointmentNotFoundException{
		PreparedStatement ps = connection.prepareStatement(SINGLE_RECORD);
		System.out.println(id);
		ps.setInt(1, id);
		ResultSet res = ps.executeQuery();
		if(res.next()) {

			MechanicEntity ms = new MechanicEntity();
			ms.setmId(res.getInt("mid"));
			ms.setFirstName(res.getString("First_name"));
			ms.setLastName(res.getString("last_name"));
			ms.setPhoneNumber(res.getString("phone_number"));
			ms.setEmailId(res.getString("emailId"));
			ms.setCity(res.getString("City"));
			ms.setMechanicName(res.getString("mechanicName"));
			ms.setAppointmentDate(res.getString("appo_date"));
			System.out.println("\n ___________________________________________");
			System.out.println("\n FirstName = "+ms.getFirstName());
		System.out.println("\n LastName = "+ms.getLastName());
		System.out.println("\n phoneNumber = "+ms.getPhoneNumber());
		System.out.println("\n Email id = "+ms.getEmailId());
		System.out.println("\n City ="+ms.getCity());
		System.out.println("\n mechanicName = "+ms.getMechanicName());
		System.out.println("\n appo_date = "+ms.getAppointmentDate());
		System.out.println("\n ___________________________________________");
		
		}
		else {
			throw new AppointmentNotFoundException("INFO OF GIVEN ID IS NOT FOUND "+id);
		}
		return true;
	}
	public List<MechanicEntity>  selectAll() throws SQLException {
		List<MechanicEntity> myList = new ArrayList<MechanicEntity>();
	PreparedStatement ps = connection.prepareStatement(SELECT_APPOINT);
	ResultSet res = ps.executeQuery();
	while(res.next()) {
		MechanicEntity ms = new MechanicEntity();
		ms.setmId(res.getInt("mid"));
		ms.setFirstName(res.getString("First_name"));
		ms.setLastName(res.getString("last_name"));
		ms.setPhoneNumber(res.getString("phone_number"));
		ms.setEmailId(res.getString("emailId"));
		ms.setCity(res.getString("city"));
		ms.setMechanicName(res.getString("mechanicName"));
		ms.setAppointmentDate(res.getString("appo_date"));
		myList.add(ms);
		
		}
	return myList;
	}
	
	
}


