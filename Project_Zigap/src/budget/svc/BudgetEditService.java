package budget.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.Date;

import budget.dao.BudgetDAO;
import category.dao.CategoryDAO;
import vo.BudgetBean;

public class BudgetEditService {
	public BudgetBean selectBudget(String id, String yyyymm,int categoryNo) throws Exception{
		
		BudgetBean budgetBean = null;
		Connection con = getConnection();
		BudgetDAO budgetDAO = BudgetDAO.getInstance();
		budgetDAO.setConnection(con);
		budgetBean = budgetDAO.selectBudget(id, yyyymm, categoryNo);
		close(con);
		return budgetBean;
	}
	
	public boolean deleteBudget(String id, String yyyymm, int categoryNo) throws Exception{
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BudgetDAO budgetDAO = BudgetDAO.getInstance();
		budgetDAO.setConnection(con);
		int deleteCount = budgetDAO.deleteBudget(id, yyyymm, categoryNo);
		
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
