package cal.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import cal.dao.CalendarDAO;
import vo.CalBean;

public class CalendarSelectMonthListService {
	
	public List<CalBean> selectList(String id, String start, String end) throws Exception{
		
		List<CalBean> list = null;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		list = calDAO.selectList(id, start, end);
		close(con);
		return list;
	}
}
