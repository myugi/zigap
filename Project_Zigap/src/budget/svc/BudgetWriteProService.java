package budget.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import budget.dao.BudgetDAO;
import vo.BudgetBean;

public class BudgetWriteProService {
	
	public boolean registBudget(BudgetBean budgetBean) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BudgetDAO budgetDAO = BudgetDAO.getInstance();
		budgetDAO.setConnection(con);
		int insertCount = budgetDAO.insertBudget(budgetBean);
		
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