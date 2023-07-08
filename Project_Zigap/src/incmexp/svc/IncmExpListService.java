package incmexp.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import incmexp.dao.IncmExpDAO;
import vo.UseIncmExpBean;

public class IncmExpListService {
	public ArrayList<UseIncmExpBean> getIncmExpList(String id) throws Exception{
		
		ArrayList<UseIncmExpBean> incmExpList = null;
		Connection con = getConnection();
		IncmExpDAO incmExpDAO = IncmExpDAO.getInstance();
		incmExpDAO.setConnection(con);
		incmExpList = incmExpDAO.incmExpList(id);
		close(con);
		return incmExpList;
	}
	
}