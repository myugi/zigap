package member.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.MemberDAO;
import vo.MemberBean;

public class MemberModifyProService {
	
	public boolean modifyMember(MemberBean member) throws Exception {
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.updateMember(member);
		System.out.println(updateCount);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
	}	
}
