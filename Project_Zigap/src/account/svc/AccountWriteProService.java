package account.svc;

import java.sql.Connection;
import account.dao.AccountDAO;
import vo.AccountBean;
import static db.JdbcUtil.*;

public class AccountWriteProService {

	public boolean registAccount(AccountBean accountBean) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		AccountDAO accountDAO = AccountDAO.getInstance();
		accountDAO.setConnection(con);
		int insertCount = accountDAO.insertAccount(accountBean);
		
		if(insertCount > 0) {
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
