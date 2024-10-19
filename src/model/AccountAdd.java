package model;

import dao.AccountsDAO;

public class AccountAdd {
	public void execute(Account account) {
		AccountsDAO dao = new AccountsDAO();
		dao.create(account);
	}

}
