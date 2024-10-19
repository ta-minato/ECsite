package model;

import dao.UserLogDAO;

public class UserLogAdd {
	public void execute(UserLog userLog) {
		UserLogDAO dao = new UserLogDAO();
		dao.create(userLog);
	}
}
