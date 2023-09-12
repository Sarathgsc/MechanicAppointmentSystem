package mechanicdao;
import java.sql.SQLException;
import java.util.List;
import entity.MechanicEntity;
import exceptions.AppointmentNotFoundException;

public interface MechanicDao {
	boolean bookappoint(MechanicEntity msEntity) throws SQLException,AppointmentNotFoundException;
	List<MechanicEntity> selectAll() throws SQLException; 
	boolean delete(int dId) throws SQLException, AppointmentNotFoundException;
	boolean singleRecord(int id) throws SQLException,AppointmentNotFoundException;
	void modify(int id)throws SQLException,AppointmentNotFoundException;


}
