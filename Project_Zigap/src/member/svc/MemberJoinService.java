package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberJoinService {

	public boolean memberJoin(String id, String pass, String name, String email) throws Exception {

		Connection con = getConnection();
		MemberDAO joinDAO = MemberDAO.getInstance();
		joinDAO.setConnection(con);
		boolean joinchk = joinDAO.memberJoin(id,pass,name,email);
		if(joinchk==true) con.commit();
		else con.rollback();
		close(con);
		return joinchk;
		
	}
	
}
