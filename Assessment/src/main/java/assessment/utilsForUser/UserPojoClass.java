package assessment.utilsForUser;

public class UserPojoClass 

{
	private String emailAddress;
	private String firstName;
	private String lastName;
	private String userId;
	public UserPojoClass() {}
	
	public UserPojoClass(String emailAddress, String firstName, String lastName, String userId) {
		super();
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}

	

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	};

	@Override
	public String toString() {
		return "UserPojoClass [emailAddress=" + emailAddress + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userId=" + userId + "]";
	}
	

}
