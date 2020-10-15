package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Profile;


public class ProfileDAO extends DAO { 

	public ProfileDAO() {
		super();
	}
	
	public void addProfile(Profile p) throws SQLException {
		
		String insert_profile = "INSERT INTO profile.data " + 
				"(firstName, lastName, line1, line2, town, regionName, postalCode, CC, CCSecurityCode, phone, email) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		stm = connector.prepareStatement(insert_profile);

		stm.setString(1, p.getFirstName());
		stm.setString(2, p.getLastName());
		stm.setString(3, p.getLine1());
		stm.setString(4, p.getLine2());
		stm.setString(5, p.getTown());
		stm.setString(6, p.getRegionName());
		stm.setString(7, p.getPostalCode());
		stm.setString(8, p.getCc());
		stm.setString(9, p.getCcSecurityCode());
		stm.setString(10, p.getPhone());
		stm.setString(11, p.getEmailAddress());
		

		stm.executeUpdate();
		stm.close();
		}
	
	
	
	public List<Profile> viewProfiles() throws SQLException{
		List<Profile> data = new ArrayList<Profile>();
		String query = "SELECT firstName, lastName, line1, line2, town, regionName, postalCode, CC, CCSecurityCode, phone, email"
				+ " FROM profile.data";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		
		while(rs.next()) {
			Profile p = new Profile();
			p.setFirstName(rs.getString(1));
			p.setLastName(rs.getString(2));
			p.setLine1(rs.getString(3));
			p.setLine2(rs.getString(4));
			p.setTown(rs.getString(5));
			p.setRegionName(rs.getString(6));
			p.setPostalCode(rs.getString(7));
			p.setCc(rs.getString(8));
			p.setCcSecurityCode(rs.getString(9));
			p.setPhone(rs.getString(10));
			p.setEmailAddress(rs.getString(11));
			data.add(p);
		}
		return data;
	}
	
	public void deleteProfile(int profileId) throws SQLException{
		String query = "DELETE FROM profile.data where profileId=?";
		stm = connector.prepareStatement(query);
		stm.setInt(1, profileId);
		
		stm.execute();
		stm.close();
		
	}

	public void updateProfile(Profile p) throws SQLException {
		String update = "UPDATE profile.data SET firstName = ?, lastName = ?,"
				+ "line1 = ?, line2 = ?, town = ?, regionName = ?, postalCode = ?,"
				+ "CC = ?, CCSecurityCode = ?, phone = ?, email = ? WHERE profileId = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1, p.getFirstName());
		stm.setString(2, p.getLastName());
		stm.setString(3, p.getLine1());
		stm.setString(4, p.getLine2());
		stm.setString(5, p.getTown());
		stm.setString(6, p.getRegionName());
		stm.setString(7, p.getPostalCode());
		stm.setString(8, p.getCc());
		stm.setString(9, p.getCcSecurityCode());
		stm.setString(10, p.getPhone());
		stm.setString(11, p.getEmailAddress());
		
		stm.executeUpdate();
		stm.close();
	}
}
