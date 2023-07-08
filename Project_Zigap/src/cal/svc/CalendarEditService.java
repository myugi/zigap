package cal.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import cal.dao.CalendarDAO;
import vo.CalBean;

public class CalendarEditService {
	
	public CalBean selectIncmExp(String id, String sequenceNo) throws Exception{
		
		CalBean incmExpBean = null;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		incmExpBean = calDAO.selectIncmExp(id, sequenceNo);
		close(con);
		return incmExpBean;
	}

	public boolean updateIncmExp(CalBean incmExp) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		int updateCount = calDAO.updateIncmExp(incmExp);
		
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


	public boolean deleteCategory(String id, String sequenceNo) throws Exception{
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		CalendarDAO calDAO = CalendarDAO.getInstance();
		calDAO.setConnection(con);
		int deleteCount = calDAO.deleteIncmExp(id, sequenceNo);
		
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
