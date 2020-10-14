package model;

public class Profile {

	public int profileNumber;
    public String firstName;
    public String lastName;
    public String line1;
    public String line2;
    public String town;
    public String regionName;
    public String postalCode;
    public String ccSecurityCode;
    public String cc;
    public String phone;
    public String emailAddress;
    
    public Profile() {}
    
	public Profile(int profileNumber, String firstName, String lastName, String line1, String line2, String town,
			String regionName, String postalCode, String ccSecurityCode, String cc, String phone, String emailAddress) {
		this.profileNumber = profileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.line1 = line1;
		this.line2 = line2;
		this.town = town;
		this.regionName = regionName;
		this.postalCode = postalCode;
		this.ccSecurityCode = ccSecurityCode;
		this.cc = cc;
		this.phone = phone;
		this.emailAddress = emailAddress;
	}

	public int getProfileNumber() {
		return profileNumber;
	}

	public void setProfileNumber(int profileNumber) {
		this.profileNumber = profileNumber;
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

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCcSecurityCode() {
		return ccSecurityCode;
	}

	public void setCcSecurityCode(String ccSecurityCode) {
		this.ccSecurityCode = ccSecurityCode;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
    
}
