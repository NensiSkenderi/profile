package dao;


public class ControlDAO {

	private static ControlDAO dao = new ControlDAO();
	private ProfileDAO profileDao = new ProfileDAO();
	
	
	public static ControlDAO getControlDao() {
		return dao;
	}

	public ProfileDAO getProfileDao() {
		return profileDao;
	}

}
