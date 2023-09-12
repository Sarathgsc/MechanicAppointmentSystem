package customerdao;
import java.sql.SQLException;
import java.util.List;

import entity.CustomerEntity;
import exceptions.CustomerNotFoundException;
public interface CustomerDao {
	
	boolean register(CustomerEntity csEntity) throws SQLException;
	List<CustomerEntity> selectAll() throws SQLException; 
	boolean delete(int dId) throws SQLException,CustomerNotFoundException;
	boolean singleRecord(int id) throws SQLException,CustomerNotFoundException;
	void modify(int id)throws SQLException,CustomerNotFoundException;

}
