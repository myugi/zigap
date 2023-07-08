package member.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberListService {

	public int getListCount() throws Exception {
		
		int listCount = 0;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		listCount = memberDAO.memberListCount();
		close(con);
		return listCount;
		
	}
	
	public ArrayList<MemberBean> getMemberList(int page, int limit) throws Exception{
		
		ArrayList<MemberBean> memberList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		memberList = memberDAO.memberList(page,limit);
		close(con);
		return memberList;
	}
}
