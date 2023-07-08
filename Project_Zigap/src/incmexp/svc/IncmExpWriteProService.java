package incmexp.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import incmexp.dao.IncmExpDAO;
import vo.UseIncmExpBean;


public class IncmExpWriteProService {
	
	public int getMaxCount() throws Exception {
		
		int maxCount = 0;
		Connection con = getConnection();
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		maxCount = incmExpDAO.selectMaxCount();
		close(con);
		return maxCount;
		
	}
	
	public boolean registIncmExp(UseIncmExpBean incmExpBean) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		int insertCount = incmExpDAO.insertIncmExp(incmExpBean);
		
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