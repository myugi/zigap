package statistics.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import budget.dao.BudgetDAO;
import statistics.dao.StatisticsDAO;
import vo.BudgetBean;
import vo.StatisticsBean;

public class StatisticsAccountService {
	public ArrayList<StatisticsBean> getList(String id, String bacctNo) throws Exception{
		
		ArrayList<StatisticsBean> list = null;
		Connection con = getConnection();
		StatisticsDAO dao = StatisticsDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectAccountList(id, bacctNo);
		close(con);
		return list;
	}
	
}
