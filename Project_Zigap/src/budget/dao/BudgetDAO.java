package budget.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BudgetBean;

public class BudgetDAO {

	DataSource ds;
	Connection con;
	private static BudgetDAO budgetDAO;
	
	private BudgetDAO() {
		
	}
	
	public static BudgetDAO getInstance() {
		if(budgetDAO == null) {
			budgetDAO = new BudgetDAO();
		}
		return budgetDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//�삁�궛紐⑸줉蹂닿린
	public ArrayList<BudgetBean> budgetList(String id, String search){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String budget_list_sql = "SELECT a.yyyymm, b.category_content, a.incm_exp, a.amt, a.category_no" + 
				"                   FROM budget a" + 
				"              LEFT JOIN category b" + 
				"                     ON a.category_no = b.category_no" + 
				"                  WHERE a.id=?" +
				"                  AND a.yyyymm=?" + 
				"               ORDER BY yyyymm;";
		ArrayList<BudgetBean> budgetList = new ArrayList<BudgetBean>();
		BudgetBean budget = null;
		
		try {
			pstmt = con.prepareStatement(budget_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, search);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				budget = new BudgetBean();
				budget.setYYYYMM(rs.getString("yyyymm"));
				budget.setCATEGORY_CONTENT(rs.getString("category_content"));
				budget.setINCM_EXP(rs.getString("incm_exp"));
				budget.setAMT(rs.getInt("amt"));
				budget.setCATEGORY_NO(rs.getInt("category_no"));
				budgetList.add(budget);
			}
		}catch(Exception ex) {
			System.out.println("getBudgetList �뿉�윭 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return budgetList;
	}
	
	//�삁�궛 �벑濡�
		public int insertBudget(BudgetBean budget) {
			
			PreparedStatement pstmt = null;
			String sql = "";
			int insertCount = 0;
			
			try {
				sql = "insert into budget(YYYYMM, CATEGORY_NO, INCM_EXP, ID, AMT) "
						+ "values(REPLACE(?,'/',''),?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, budget.getYYYYMM());
				pstmt.setInt(2, budget.getCATEGORY_NO());
				pstmt.setString(3, budget.getINCM_EXP());
				pstmt.setString(4, budget.getBUDGET_ID());
				pstmt.setInt(5, budget.getAMT());
				System.out.println(sql);
				insertCount = pstmt.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println("accountInsert �뿉�윭 : " + ex);
			}finally {
				close(pstmt);
			}
			return insertCount;
		}
	
	//議고쉶�븯湲�
		public BudgetBean selectBudget(String id, String yyyymm, int categoryNo) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BudgetBean budget = null;
			String budget_list_sql = "SELECT * FROM budget WHERE id=? AND yyyymm = REPLACE(?,'/','') AND category_no = ?";

			try {
				pstmt = con.prepareStatement(budget_list_sql);
				pstmt.setString(1, id);
				pstmt.setString(2, yyyymm);
				pstmt.setInt(3, categoryNo);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					budget = new BudgetBean();
					budget.setYYYYMM(rs.getString("yyyymm"));
					budget.setBUDGET_ID(rs.getString("id"));
					budget.setCATEGORY_NO(rs.getInt("category_no"));
					budget.setINCM_EXP(rs.getString("incm_exp"));
					budget.setAMT(rs.getInt("amt"));			
				}
			}catch(Exception ex) {
				System.out.println("budgetSelect �뿉�윭 : " + ex);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return budget;
		}
		
	//怨꾩쥖�젙蹂� �궘�젣
	public int deleteBudget(String id, String yyyymm, int categoryNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCount = 0;
		
		try {
			sql = "delete from budget where id = ? and yyyymm = ? and category_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, yyyymm.replace("/", ""));
			pstmt.setInt(3, categoryNo);
			
			deleteCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountDelete �뿉�윭 : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
}
