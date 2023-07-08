package cal.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import cal.dao.CalendarDAO;
import vo.CalBean;

public class CalendarWriteProService {
public int getMaxCount() throws Exception {
		
		int maxCount = 0;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		maxCount = calDAO.selectMaxCount();
		close(con);
		return maxCount;
		
	}
	
	public boolean registIncmExp(CalBean incmExpBean) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		int insertCount = calDAO.insertIncmExp(incmExpBean);
		
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