package entity;
public class MechanicEntity extends Object{
	//Instance variables we will use
	private int mId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailId;
	private String City;
	private String appointmentDate;
	private String mechanicName;
	public String getMechanicName() {
		return mechanicName;
	}
	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}
	
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailid) {
		this.emailId = emailid;
	}
	
//	@Override
	public String toString() {
		return "Mechanic [id = " + mId + ", firstName = " + firstName + ", lastName = " + lastName +", phoneNumber = " + phoneNumber +", EmailId = "+emailId+", city = "+City+", Mechanic Name is "+mechanicName+", appointment date is "+appointmentDate
				+ "]";
	}
	

}

