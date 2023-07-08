package budget.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import budget.dao.BudgetDAO;
import vo.BudgetBean;

public class BudgetListService {
	public ArrayList<BudgetBean> getBudgetList(String id, String search) throws Exception{
		
		ArrayList<BudgetBean> budgetList = null;
		Connection con = getConnection();
		BudgetDAO budgetDAO = BudgetDAO.getInstance();
		budgetDAO.setConnection(con);
		budgetList = budgetDAO.budgetList(id, search);
		close(con);
		return budgetList;
	}
	
}
