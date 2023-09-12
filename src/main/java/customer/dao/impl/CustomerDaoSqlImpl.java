package customer.dao.impl;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbutil.Dbutil;
import customerdao.CustomerDao;
import pages.Customer;
import entity.CustomerEntity;
import exceptions.CustomerNotFoundException;

public class CustomerDaoSqlImpl implements CustomerDao {
	private static final String INSERT_CUSTOMER="insert into customers(first_name,last_name,age,phone_number,address )values(?,?,?,?,?)";
	private static final String SELECT_CUSTOMER="select * from customers";
	private static final String DELETE_CUSTOMER="DELETE FROM customers WHERE id=?";
	private static final String SINGLE_RECORD="select * from customers WHERE id=?";
	private static String UPDATE_CUSTOMER = null;
	private static Scanner sc = new Scanner(System.in);
	private  Connection connection=Dbutil.getConnection(); 
	
	public boolean register(CustomerEntity csEntity) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER);
		ps.setString(1, csEntity.getFirstName());
		ps.setString(2, csEntity.getLastName());
		ps.setInt(3, csEntity.getAge());
		ps.setString(4,csEntity.getPhoneNumber());
		ps.setString(5, csEntity.getAddress());
		int excecuteUpdate = ps.executeUpdate();
		ps.close();
		if(excecuteUpdate>0) {
			return true;
		}
		return false;
	}
	
	public void modify(int id) throws SQLException,CustomerNotFoundException{
		while(true) {
			System.out.println("Choose option to update");
			System.out.println("\n ___________________________________________");
			System.out.println("\n 1.FirstName");
			System.out.println("\n 2.LastName");
			System.out.println("\n 3.Age");
			System.out.println("\n 4.PhoneNumber");
			System.out.println("\n 5.Address");
			System.out.println("\n 0.back to main menu");
			System.out.println("\n ___________________________________________");
			
			int uid=Integer.parseInt(sc.nextLine());
			switch(uid) {
			case 1:

			System.out.println("Enter new first name");
			String newName = sc.nextLine();
			UPDATE_CUSTOMER="update customers set first_name=? where id=?";
			PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER);
			ps.setString(1,newName);
			ps.setInt(2,id);
			ps.executeUpdate();
			System.out.println("new updated details are ");
			singleRecord(id);
			break;
			case 2:
				System.out.println("Enter new last name");
				String newLastName = sc.nextLine();
				PreparedStatement ps1 = connection.prepareStatement("update customers set last_name=? where id=?");
				ps1.setString(1,newLastName);
				ps1.setInt(2,id);
				 ps1.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 3:
				System.out.println("Enter new age");
				int newage = Integer.parseInt(sc.nextLine());
				PreparedStatement ps2 = connection.prepareStatement("update customers set age=? where id=?");
				ps2.setInt(1,newage);
				ps2.setInt(2,id);
				 ps2.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 4:
				System.out.println("Enter new phone number");
				String newphonenumber  = sc.nextLine();
				PreparedStatement ps3 = connection.prepareStatement("update customers set phone_number=? where id=?");
				ps3.setString(1,newphonenumber);
				ps3.setInt(2,id);
				ps3.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 5:
				System.out.println("Enter new Address");
				String newaddress  = sc.nextLine();
				PreparedStatement ps4 = connection.prepareStatement("update customers set address=? where id=?");
				ps4.setString(1,newaddress);
				ps4.setInt(2,id);
				ps4.executeUpdate();
				System.out.println("new updated details are ");
				singleRecord(id);
				break;
			case 0:
				Customer.menu();
				default:
					System.out.println("Try again");
			}
		}
	}
	public boolean delete(int id) throws SQLException,CustomerNotFoundException{
		PreparedStatement ps = connection.prepareStatement(DELETE_CUSTOMER);
		ps.setInt(1,id);
		int executeUpdate = ps.executeUpdate();
		System.out.println("Rows effected "+ executeUpdate);
		System.out.println("DELETE COMPLETE");
				ps.close();
		if(executeUpdate>0) {
			return true ;
		}
		throw new CustomerNotFoundException("INFO OF GIVEN ID "+id+" ID IS NOT FOUND TO DELETE");
	}
	public boolean singleRecord(int id) throws SQLException, CustomerNotFoundException{
		PreparedStatement ps = connection.prepareStatement(SINGLE_RECORD);
		ps.setInt(1, id);
		
		ResultSet res = ps.executeQuery();
		if(res.next()) {
			CustomerEntity cs = new CustomerEntity();
			cs.setId(res.getInt("id"));
			cs.setFirstName(res.getString("First_name"));
			cs.setLastName(res.getString("last_name"));
			cs.setAge(res.getInt("age"));
			cs.setPhoneNumber(res.getString("phone_number"));
			cs.setAddress(res.getString("Address"));
		//System.out.println("Your details are: Id : "+cs.getId()+" firstName= " + cs.getFirstName() + ", lastName= " + cs.getLastName() + ",Age= " + cs.getAge() +", phoneNumber= " + cs.getPhoneNumber() + ",Address= " + cs.getAddress() );
			System.out.println("\n ___________________________________________");
		System.out.println("\n FirstName = "+cs.getFirstName());
		System.out.println("\n lastName = "+cs.getLastName());
		System.out.println("\n age = "+cs.getAge());
		System.out.println("\n phone number = "+cs.getPhoneNumber());
		System.out.println("\n address = "+cs.getAddress());
		System.out.println("\n ___________________________________________");
		
		}
		else {
			throw new CustomerNotFoundException("INFO OF GIVEN ID "+id+" IS NOT FOUND" );
		}
		return true;
	}

	
	public List<CustomerEntity> selectAll() throws SQLException {
		List<CustomerEntity> myList = new ArrayList<CustomerEntity>();
	PreparedStatement ps = connection.prepareStatement(SELECT_CUSTOMER);
	ResultSet res = ps.executeQuery();
	while(res.next()) {
		CustomerEntity cs = new CustomerEntity();
		cs.setId(res.getInt("id"));
		cs.setFirstName(res.getString("First_name"));
		cs.setLastName(res.getString("last_name"));
		cs.setAge(res.getInt("age"));
		cs.setPhoneNumber(res.getString("phone_number"));
		cs.setAddress(res.getString("Address"));
		myList.add(cs);
		}
	return myList;
	}

	
}
