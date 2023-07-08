package cal.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import cal.dao.CalendarDAO;
import vo.CalBean;

public class CalendarSelectDateListService {
	
	public List<CalBean> selectList(String id, String dt, String incmExp) throws Exception{
		
		List<CalBean> list = null;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		list = calDAO.selectDtList(id, dt, incmExp);
		close(con);
		return list;
	}
}
