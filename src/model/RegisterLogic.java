package model;

import dao.AccountsDAO;

public class RegisterLogic {
	public Account execute(Login login) {
		AccountsDAO dao = new AccountsDAO();
		Account account = dao.findByRegister(login);
		return account;
	}
}
