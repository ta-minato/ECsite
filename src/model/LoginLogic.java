package model;

import dao.AccountsDAO;

public class LoginLogic {
	public Account execute(Login login) {
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.findByLogin(login);
		return account;
	}
}
