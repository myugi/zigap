package account.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import account.dao.AccountDAO;
import category.dao.CategoryDAO;
import vo.AccountBean;

public class AccountListService {

	public ArrayList<AccountBean> getAccountList(String id) throws Exception{
		
		ArrayList<AccountBean> accountList = null;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		accountList = accountDAO.accountList(id);
		close(con);
		return accountList;
	}
	
	public String getBankNm(String bacctNo) throws Exception{
		
		String bankNm = null;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		bankNm = accountDAO.getBankNm(bacctNo);
		close(con);
		return bankNm;
	}
}
