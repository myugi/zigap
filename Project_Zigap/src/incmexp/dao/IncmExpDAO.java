package incmexp.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.AccountBean;
import vo.CategoryBean;
import vo.UseIncmExpBean;

public class IncmExpDAO {

	DataSource ds;
	Connection con;
	private static IncmExpDAO incmExpDAO;
	
	private IncmExpDAO() {
		
	}
	
	public static IncmExpDAO getInstance() {
		if(incmExpDAO == null) {
			incmExpDAO = new IncmExpDAO();
		}
		return incmExpDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//고정수입/지출의 개수 구하기
		public int selectMaxCount() {
			int maxCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String max_count_sql = "SELECT IFNULL(MAX(sequence_no),0)+1 FROM use_incm_exp";
			
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
			
	//고정수입/지출 목록보기
	public ArrayList<UseIncmExpBean> incmExpList(String id){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String incmexp_list_sql = "SELECT a.sequence_no, a.incm_exp, b.category_content, a.bacct_no, a.use_date, a.use_amt, a.COMMENT, c.bank_name" + 
				"                    FROM use_incm_exp a" + 
				"               LEFT JOIN category b" + 
				"                      ON a.category_no = b.category_no" + 
				"               LEFT JOIN account c" + 
				"                      ON a.bacct_no = c.bacct_no" + 
				"                   WHERE a.id=?" + 
				"                ORDER BY a.incm_exp";
		ArrayList<UseIncmExpBean> incmExpList = new ArrayList<UseIncmExpBean>();
		UseIncmExpBean incmexp = null;
		
		try {
			pstmt = con.prepareStatement(incmexp_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				incmexp = new UseIncmExpBean();
				incmexp.setSEQUENCE_NO(rs.getInt("sequence_no"));
				incmexp.setINCM_EXP(rs.getString("incm_exp"));
				incmexp.setCATEGORY_CONTENT(rs.getString("category_content"));
				incmexp.setBACCT_NO(rs.getString("bacct_no"));
				incmexp.setUSE_DATE(rs.getString("use_date"));
				incmexp.setUSE_AMT(rs.getInt("use_amt"));
				incmexp.setCOMMENT(rs.getString("comment"));
				incmexp.setBANK_NAME(rs.getString("bank_name"));
				incmExpList.add(incmexp);
			}
		}catch(Exception ex) {
			System.out.println("getBudgetList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return incmExpList;
	}
	
	//고정수입/지출 등록
	public int insertIncmExp(UseIncmExpBean incmExpBean) {
		
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into use_incm_exp(SEQUENCE_NO,ID,INCM_EXP,CATEGORY_NO,BACCT_NO,USE_DATE,USE_AMT,COMMENT) values(?,?,?,?,?,REPLACE(?,'-',''),?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, incmExpBean.getSEQUENCE_NO());
			pstmt.setString(2, incmExpBean.getID());
			pstmt.setString(3, incmExpBean.getINCM_EXP());
			pstmt.setInt(4, incmExpBean.getCATEGORY_NO());
			pstmt.setString(5, incmExpBean.getBACCT_NO());
			pstmt.setString(6, incmExpBean.getUSE_DATE());
			pstmt.setInt(7, incmExpBean.getUSE_AMT());
			pstmt.setString(8, incmExpBean.getCOMMENT());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountInsert 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
	
	//고정수입/지출 정보 수정
	public int updateIncmExp(UseIncmExpBean incmExp) {
		PreparedStatement pstmt = null;
		String sql="";
		int updateCount = 0;
		
		try {
			sql = "UPDATE use_incm_exp SET incm_exp=?, category_no=?, bacct_no=?, use_date=?, use_amt=?, comment=? WHERE id = ? AND sequence_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, incmExp.getINCM_EXP());
			pstmt.setInt(2, incmExp.getCATEGORY_NO());
			pstmt.setString(3, incmExp.getBACCT_NO());
			pstmt.setString(4, incmExp.getUSE_DATE());
			pstmt.setInt(5, incmExp.getUSE_AMT());
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
		
	//조회하기
	public UseIncmExpBean selectIncmExp(String id, String sequenceNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UseIncmExpBean incmExp = null;
		String incmExp_list_sql = "SELECT * FROM use_incm_exp WHERE id=? AND sequence_no = ?";

		try {
			pstmt = con.prepareStatement(incmExp_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, sequenceNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				incmExp = new UseIncmExpBean();
				incmExp.setSEQUENCE_NO(rs.getInt("sequence_no"));
				incmExp.setID(rs.getString("id"));
				incmExp.setBACCT_NO(rs.getString("bacct_no"));
				incmExp.setINCM_EXP(rs.getString("incm_exp"));
				incmExp.setCATEGORY_NO(rs.getInt("category_no"));
				incmExp.setUSE_DATE(rs.getString("use_date"));
				incmExp.setUSE_AMT(rs.getInt("use_amt"));
				incmExp.setCOMMENT(rs.getString("comment"));
			}
		}catch(Exception ex) {
			System.out.println("accountSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return incmExp;
	}
	
	//고정수입/지출 삭제
	public int deleteIncmExp(String id, String sequenceNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCount = 0;
		
		try {
			sql = "delete from use_incm_exp where id = ? and sequence_no = ?";
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
