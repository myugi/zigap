package statistics.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import budget.dao.BudgetDAO;
import statistics.dao.StatisticsDAO;
import vo.BudgetBean;
import vo.StatisticsBean;

public class StatisticsBudgetService {
	public ArrayList<StatisticsBean> getList(String id, String date, String incmExp) throws Exception{
		
		ArrayList<StatisticsBean> list = null;
		Connection con = getConnection();
		StatisticsDAO dao = StatisticsDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectBudgetList(id, date, incmExp);
		close(con);
		return list;
	}
	
}
