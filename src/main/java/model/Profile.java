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
    public String countryCode;
    public String ccSecurityCode;
    public String ccNumber;
    public String ccTypeName;
    public String ccTypeCode;
    public String ccExpMonth;
    public String ccExpYear;
    public String phone;
    public String emailAddress;
    
    public Profile() {}
    
	public Profile(int profileNumber, String firstName, String lastName, String line1, String line2, String town,
			String regionName, String postalCode, String ccSecurityCode, String phone, String emailAddress) {
		this.profileNumber = profileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.line1 = line1;
		this.line2 = line2;
		this.town = town;
		this.regionName = regionName;
		this.postalCode = postalCode;
		this.ccSecurityCode = ccSecurityCode;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcTypeName() {
		return ccTypeName;
	}

	public void setCcTypeName(String ccTypeName) {
		this.ccTypeName = ccTypeName;
	}

	public String getCcTypeCode() {
		return ccTypeCode;
	}

	public void setCcTypeCode(String ccTypeCode) {
		this.ccTypeCode = ccTypeCode;
	}

	public String getCcExpMonth() {
		return ccExpMonth;
	}

	public void setCcExpMonth(String ccExpMonth) {
		this.ccExpMonth = ccExpMonth;
	}

	public String getCcExpYear() {
		return ccExpYear;
	}

	public void setCcExpYear(String ccExpYear) {
		this.ccExpYear = ccExpYear;
	}
	
    
}
