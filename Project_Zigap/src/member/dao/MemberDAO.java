package member.dao;

import static db.JdbcUtil.close;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.MemberBean;

public class MemberDAO {

	DataSource ds;
	Connection con;


	private static MemberDAO loginDAO;

	public static MemberDAO getInstance() {
		if (loginDAO == null) {
			loginDAO = new MemberDAO();
		}
		return loginDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	//id와 password를 검색한 뒤 member에 셋팅하기
	public MemberBean idCheck(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean member = new MemberBean();

		try {
			pstmt = con.prepareStatement("SELECT * FROM member WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()){
					member.setId(id);
					member.setPassword(rs.getString("password"));
			}
		} catch (Exception ex) {
			System.out.println("DAO idCheck 에러 : " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	//회원가입
	public boolean memberJoin(String id, String pass, String name, String email) {
		PreparedStatement pstmt = null;
		boolean joinchk = false;
		InetAddress local;
		try {
			pstmt = con.prepareStatement("INSERT INTO member (id, password, name, email, user_role, crt_id, crt_ip) VALUES (?,?,?,?,?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			//권한 등급
			if(id.equals("admin")) pstmt.setString(5, "관리자");
			else pstmt.setString(5, "일반");
			pstmt.setString(6, "_");

			local = InetAddress.getLocalHost();
			String ip = local.getHostAddress();
			pstmt.setString(7, ip);

			int result = pstmt.executeUpdate();

			if(result!=0){
				joinchk = true;
			}else{
				System.out.println("쿼리문 실행 X");
				System.out.println(id);
				System.out.println(pass);
			}
		} catch (Exception ex) {
			System.out.println("DAO memberJoin 에러 : " + ex);
			System.out.println(id);
			System.out.println(pass);
		} finally {
			close(pstmt);
		}
		return joinchk;
	}

	//회원 수 구하기
		public int memberListCount() {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				pstmt = con.prepareStatement("select count(*) from member");
				rs = pstmt.executeQuery();

				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			}catch(Exception ex) {
				System.out.println("getListCount 에러 : " + ex);
			}finally {
				close(rs);
				close(pstmt);
			}

			return listCount;

		}

	//회원목록보기
	public ArrayList<MemberBean> memberList(int page, int limit){

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String member_list_sql = "SELECT * FROM member";
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			MemberBean member = null;
			int startrow = (page-1)*10; //읽기 시작할 row번호

			try {
				pstmt = con.prepareStatement(member_list_sql);

				rs = pstmt.executeQuery();

				while(rs.next()) {
					member = new MemberBean();
					member.setId(rs.getString("id"));
					member.setPassword(rs.getString("password"));
					member.setName(rs.getString("name"));
					member.setEmail(rs.getString("email"));
					memberList.add(member);
				}
			}catch(Exception ex) {
				System.out.println("getMemberList 에러 : " + ex);
			}finally {
				close(rs);
				close(pstmt);
			}

			return memberList;
		}

	//회원정보 상세보기
	public MemberBean memberInfo(String id){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean member = null;

		try {
			pstmt = con.prepareStatement("SELECT * FROM member WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				member = new MemberBean();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
			}
		} catch (Exception ex) {
			System.out.println("DAO idCheck 에러 : " + ex);
			System.out.println(id);
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	//회원정보 수정하기
	public int updateMember(MemberBean member) {

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set password=?, name=?, email=? where id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("memberModify 에러 : " + ex);
		}finally {
			close(pstmt);
		}

		return updateCount;
	}

	//회원수정권한
	public boolean isMember(String id, String pass) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String ismember_sql = "select * from member where id=?";
		boolean isMember = false;

		try {
			pstmt = con.prepareStatement(ismember_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("password"))) {
				isMember = true;
			}
		}catch(Exception ex) {
			System.out.println("isMember 에러 : " + ex);
		}finally {
			close(pstmt);
		}

		return isMember;
	}

	//회원 삭제하기
	public int deleteMember(String id) {

		PreparedStatement pstmt = null;
		String member_delete_sql = "delete from member where id=?";
		int deleteCount = 0;

		try {
			pstmt = con.prepareStatement(member_delete_sql);
			pstmt.setString(1, id);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("memberDelete 에러 : " + ex);
		}finally {
			close(pstmt);
		}

		return deleteCount;
	}

}
