package cal.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.CalBean;

public class CalendarDAO {

	DataSource ds;
	Connection con;
	private static CalendarDAO caledarDAO;
	
	private CalendarDAO() {
		
	}
	
	public static CalendarDAO getInstance() {
		if(caledarDAO == null) {
			caledarDAO = new CalendarDAO();
		}
		return caledarDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//수입/지출의 개수 구하기
	public int selectMaxCount() {
		int maxCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String max_count_sql = "SELECT IFNULL(MAX(sequence_no),0)+1 FROM incm_exp";
		
		try {
			
			pstmt = con.prepareStatement(max_count_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxCount = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return maxCount;
	}
	
	//고정수입/지출 등록
	public int insertIncmExp(CalBean incmExpBean) {
		
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into incm_exp(SEQUENCE_NO,ID,INCM_EXP,CATEGORY_NO,BACCT_NO,INCM_EXP_DATE,AMT,COMMENT) values(?,?,?,?,?,REPLACE(?, '-', ''),REPLACE(?, ',', ''),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, incmExpBean.getSEQUENCE_NO());
			pstmt.setString(2, incmExpBean.getID());
			pstmt.setString(3, incmExpBean.getINCM_EXP());
			pstmt.setInt(4, incmExpBean.getCATEGORY_NO());
			pstmt.setString(5, incmExpBean.getBACCT_NO());
			pstmt.setString(6, incmExpBean.getINCM_EXP_DATE());
			pstmt.setInt(7, incmExpBean.getAMT());
			pstmt.setString(8, incmExpBean.getCOMMENT());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("calendarInsert 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	//조회하기
	public CalBean selectIncmExp(String id, String sequenceNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalBean incmExp = null;
		String incmExp_list_sql = "SELECT * FROM incm_exp WHERE id=? AND sequence_no = ?";

		try {
			pstmt = con.prepareStatement(incmExp_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, sequenceNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				incmExp = new CalBean();
				incmExp.setSEQUENCE_NO(rs.getInt("sequence_no"));
				incmExp.setID(rs.getString("id"));
				incmExp.setBACCT_NO(rs.getString("bacct_no"));
				incmExp.setINCM_EXP(rs.getString("incm_exp"));
				incmExp.setCATEGORY_NO(rs.getInt("category_no"));
				incmExp.setINCM_EXP_DATE(rs.getString("incm_exp_date"));
				incmExp.setAMT(rs.getInt("amt"));
				incmExp.setCOMMENT(rs.getString("comment"));
			}
		}catch(Exception ex) {
			System.out.println("calendarSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return incmExp;
	}
	
	public List<CalBean> selectList(String id, String start, String end) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalBean incmExp = null;
		List list = new ArrayList<CalBean>();
		String incmExp_list_sql = " SELECT concat(substr(incm_exp_date, 1, 4), '-', substr(incm_exp_date, 5, 2), '-', substr(incm_exp_date, 7, 2)) as start" + 
								 "       , CONCAT(`incm_exp`, ': ', FORMAT( SUM(amt), 0)) AS title" + 
								 "       , CASE WHEN INCM_EXP = '수입' THEN '#86BCFA' ELSE '#FD9B9B' END AS color" +
								 "       , incm_exp" +
								 "    FROM incm_exp" + 
								 "   WHERE id = ?" + 
								 "     AND incm_exp_date BETWEEN ? AND ?" + 
								 "   GROUP by incm_Exp_date, incm_exp";
		try {
			pstmt = con.prepareStatement(incmExp_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				incmExp = new CalBean();
				incmExp.setStart(rs.getString("start"));
				incmExp.setTitle(rs.getString("title"));
				incmExp.setColor(rs.getString("color"));
				incmExp.setINCM_EXP(rs.getString("incm_exp"));
				list.add(incmExp);
			}
		}catch(Exception ex) {
			System.out.println("calendarSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<CalBean> selectDtList(String id, String dt, String incm_exp) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CalBean incmExp = null;
		List list = new ArrayList<CalBean>();
		String incmExp_list_sql = " SELECT a.sequence_no, a.id, a.incm_exp, a.category_no, a.bacct_no" + 
								"      , concat(substr(incm_exp_date, 1, 4), '-', substr(incm_exp_date, 5, 2), '-', substr(incm_exp_date, 7, 2)) AS incm_Exp_date, a.amt AS amt, a.COMMENT, b.category_content, c.bank_name" + 
								"   FROM incm_exp a LEFT JOIN category b" + 
								"     ON a.category_no = b.category_no" + 
								"   LEFT JOIN `account` c" + 
								"     ON a.bacct_no = c.bacct_no" + 
								" WHERE a.id = ?"
								+ "AND a.incm_exp_date = REPLACE(?, '-', '')" + 
								" AND a.incm_exp = ?" ;
		
		System.out.println(incmExp_list_sql);
		try {
			pstmt = con.prepareStatement(incmExp_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, dt);
			pstmt.setString(3, incm_exp);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				incmExp = new CalBean();
				incmExp.setSEQUENCE_NO(rs.getInt("sequence_no"));
				incmExp.setID(rs.getString("id"));
				incmExp.setBACCT_NO(rs.getString("bacct_no"));
				incmExp.setINCM_EXP(rs.getString("incm_exp"));
				incmExp.setCATEGORY_NO(rs.getInt("category_no"));
				incmExp.setINCM_EXP_DATE(rs.getString("incm_exp_date"));
				incmExp.setAMT(rs.getInt("amt"));
				incmExp.setCOMMENT(rs.getString("comment"));
				incmExp.setBANK_NAME(rs.getString("BANK_NAME"));
				incmExp.setCATEGORY_CONTENT(rs.getString("CATEGORY_CONTENT"));
				list.add(incmExp);
			}
		}catch(Exception ex) {
			System.out.println("calendarSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}	
	//수입/지출 정보 수정
	public int updateIncmExp(CalBean incmExp) {
		PreparedStatement pstmt = null;
		String sql="";
		int updateCount = 0;
		
		try {
			sql = "UPDATE incm_exp SET incm_exp=?, category_no=?, bacct_no=?, incm_exp_date=?, amt=?, comment=? WHERE id = ? AND sequence_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, incmExp.getINCM_EXP());
			pstmt.setInt(2, incmExp.getCATEGORY_NO());
			pstmt.setString(3, incmExp.getBACCT_NO());
			pstmt.setString(4, incmExp.getINCM_EXP_DATE());
			pstmt.setInt(5, incmExp.getAMT());
			pstmt.setString(6, incmExp.getCOMMENT());
			pstmt.setString(7, incmExp.getID());
			pstmt.setInt(8, incmExp.getSEQUENCE_NO());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("accountInsert 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}	
	
	//수입/지출 삭제
	public int deleteIncmExp(String id, String sequenceNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCount = 0;
		
		try {
			sql = "delete from incm_exp where id = ? and sequence_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, sequenceNo);
			
			deleteCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountDelete 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
		
}
