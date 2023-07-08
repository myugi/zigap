package log.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import vo.MemberBean;

public class LoginService {

	public MemberBean loginCheck(String id) throws Exception {

		Connection con = getConnection();
		MemberDAO loginDAO = MemberDAO.getInstance();
		loginDAO.setConnection(con);
		MemberBean member = loginDAO.idCheck(id);
		close(con);
		return member;
		
	}
	
}
