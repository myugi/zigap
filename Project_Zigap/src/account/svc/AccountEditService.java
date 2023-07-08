package account.svc;

import java.sql.Connection;
import java.util.ArrayList;

import account.dao.AccountDAO;
import vo.AccountBean;
import static db.JdbcUtil.*;

public class AccountEditService {

	public AccountBean selectAccount(String id, String bacctNo) throws Exception{
		
		AccountBean accountBean = null;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		accountBean = accountDAO.selectAccount(id, bacctNo);
		close(con);
		return accountBean;
	}
	
	public boolean updateAccount(AccountBean account) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		int updateCount = accountDAO.updateAccount(account);
		
		if(updateCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}
	
	
	public boolean deleteAccount(String id, String bacctNo) throws Exception{

		boolean isWriteSuccess = false;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		int deleteCount = accountDAO.deleteAccount(id, bacctNo);
		
		if(deleteCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}
}
