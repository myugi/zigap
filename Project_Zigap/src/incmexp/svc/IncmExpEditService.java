package incmexp.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import account.dao.AccountDAO;
import incmexp.dao.IncmExpDAO;
import vo.AccountBean;
import vo.UseIncmExpBean;


public class IncmExpEditService {
public UseIncmExpBean selectIncmExp(String id, String sequenceNo) throws Exception{
		
		UseIncmExpBean incmExpBean = null;
		Connection con = getConnection();
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		incmExpBean = incmExpDAO.selectIncmExp(id, sequenceNo);
		close(con);
		return incmExpBean;
	}

	public boolean updateIncmExp(UseIncmExpBean incmExp) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		int updateCount = incmExpDAO.updateIncmExp(incmExp);
		
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
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		int deleteCount = incmExpDAO.deleteIncmExp(id, sequenceNo);
		
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
