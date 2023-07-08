package member.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberInfoService {

	public MemberBean getMemberInfo(String id) throws Exception{
		
		MemberBean memberInfo = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		memberInfo = memberDAO.memberInfo(id);
		close(con);
		return memberInfo;
	}
}
