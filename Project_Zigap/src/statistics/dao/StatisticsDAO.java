package statistics.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BudgetBean;
import vo.StatisticsBean;

public class StatisticsDAO {

	DataSource ds;
	Connection con;
	private static StatisticsDAO statisticsDAO;
	
	private StatisticsDAO() {
		
	}
	
	public static StatisticsDAO getInstance() {
		if(statisticsDAO == null) {
			statisticsDAO = new StatisticsDAO();
		}
		return statisticsDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ArrayList<StatisticsBean> selectAccountList(String id, String bacctNo){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.incm_exp_date, a.incm_exp, a.category_no, a.`comment`, b.category_content" + 
				"    , CASE a.incm_exp WHEN '수입' THEN a.amt WHEN '지출' THEN 0 END incm_amt" + 
				"    , CASE a.incm_exp WHEN '수입' THEN 0 WHEN '지출' THEN (a.amt * -1) END exp_amt" + 
				"    , (@baseAmt := @baseAmt + CASE a.incm_exp WHEN '수입' THEN a.amt WHEN '지출' THEN (a.amt * -1) END) AS jan_amt" + 
				"  FROM (SELECT ia.incm_exp_date, ia.upd_dt, ia.incm_exp, ia.category_no, ia.amt, ia.`comment`" + 
				"          FROM incm_exp ia" + 
				"         WHERE ia.bacct_no = ?" + 
				"           AND ia.id = ?" + 
				"         ORDER BY ia.incm_exp_date ASC, ia.upd_dt ASC) a " + 
				"  LEFT JOIN category b" + 
				"    ON a.category_no = b.category_no" + 
				"  LEFT JOIN (SELECT @baseAmt := base_amt" + 
				"               FROM `account`" + 
				"              WHERE bacct_no = ?" + 
				"                AND id = ? ) z" + 
				" ON 1=1";
		ArrayList<StatisticsBean> list = new ArrayList<StatisticsBean>();
		StatisticsBean bean = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bacctNo);
			pstmt.setString(2, id);
			pstmt.setString(3, bacctNo);
			pstmt.setString(4, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new StatisticsBean();
				bean.setINCM_EXP_DATE(rs.getString("incm_exp_date"));
				bean.setINCM_EXP(rs.getString("incm_exp"));
				bean.setCATEGORY_NO(rs.getInt("category_no"));
				bean.setCATEGORY_CONTENT(rs.getString("category_content"));
				bean.setCOMMENT(rs.getString("comment"));
				bean.setINCM_AMT(rs.getInt("incm_amt"));
				bean.setEXP_AMT(rs.getInt("exp_amt"));
				bean.setJAN_AMT(rs.getInt("jan_amt"));
				list.add(bean);
			}
		}catch(Exception ex) {
			System.out.println("error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<StatisticsBean> selectBudgetList(String id, String date, String incmExp){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT a.category_no, a.category_content, a.incm_exp, ifnull(b.amt, 0) AS budg_amt, ifnull(c.amt, 0) AS use_amt" + 
					"  FROM category a LEFT JOIN budget b" + 
					"    ON b.id = ?" + 
					"   AND b.yyyymm = ?" + 
					"   AND a.category_no = b.category_no" + 
					"  LEFT JOIN (SELECT ia.category_no, SUM(ia.amt) AS AMT" + 
					"                FROM incm_exp ia" + 
					"               WHERE ia.incm_exp_date LIKE CONCAT(?, '%')" + 
					"                 AND ia.id = ?" + 
					"              GROUP BY ia.category_no) c" + 
					"    ON a.category_no = c.category_no" + 
					" WHERE a.id = ?"+ 
					"   AND a.incm_exp = ?";
		ArrayList<StatisticsBean> list = new ArrayList<StatisticsBean>();
		StatisticsBean bean = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			pstmt.setString(4, id);
			pstmt.setString(5, id);
			pstmt.setString(6, incmExp);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new StatisticsBean();
				bean.setCATEGORY_NO(rs.getInt("category_no"));
				bean.setCATEGORY_CONTENT(rs.getString("category_content"));
				bean.setINCM_EXP(rs.getString("incm_exp"));
				bean.setBUDG_AMT(rs.getInt("budg_amt"));
				bean.setUSE_AMT(rs.getInt("use_amt"));
				
				list.add(bean);
			}
		}catch(Exception ex) {
			System.out.println("error : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<StatisticsBean> selectBudgetCateList(String id, String date, String incmExp){
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT a.category_no, a.category_content, a.incm_exp, IFNULL(b.amt, 0) AS AMT" + 
				"  FROM category a LEFT JOIN (SELECT ia.category_no, SUM(ia.amt) AS AMT" + 
				"                                FROM incm_exp ia" + 
				"                               WHERE ia.incm_exp_date LIKE CONCAT(?, '%')" + 
				"                                 AND ia.id = ?" + 
				"                              GROUP BY ia.category_no) b" + 
				"    ON a.category_no = b.category_no" + 
				" WHERE a.id = ?" + 
				"   AND a.incm_exp = ?";
	ArrayList<StatisticsBean> list = new ArrayList<StatisticsBean>();
	StatisticsBean bean = null;
	
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, date);
		pstmt.setString(2, id);
		pstmt.setString(3, id);
		pstmt.setString(4, incmExp);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			bean = new StatisticsBean();
			bean.setCATEGORY_NO(rs.getInt("category_no"));
			bean.setCATEGORY_CONTENT(rs.getString("category_content"));
			bean.setINCM_EXP(rs.getString("incm_exp"));
			bean.setAMT(rs.getInt("amt"));
			
			list.add(bean);
		}
	}catch(Exception ex) {
		System.out.println("error : " + ex);
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return list;
}

	public ArrayList<StatisticsBean> selectPremonthList(String id, String date, String incmExp){
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT a.category_no, a.category_content, a.incm_exp, ifnull(b.amt, 0) AS pre_amt, ifnull(c.amt, 0) AS cur_amt, " +
				"        case a.incm_Exp " +
				"          when '수입' then " +
				"             case when ifnull(b.amt, 0) < ifnull(c.amt, 0) then '#0000f0'" + 
				"                  when ifnull(b.amt, 0) > ifnull(c.amt, 0) then '#f00000'" +
				"             else '' end" +
				"          when '지출' then " +
				"             case when ifnull(b.amt, 0) < ifnull(c.amt, 0) then '#f00000'" + 
				"                  when ifnull(b.amt, 0) > ifnull(c.amt, 0) then '#0000f0'" +
				"             else '' end" +
				"          else '' end AS COLOR" +
				"  FROM category a LEFT JOIN (SELECT ia.category_no, SUM(ia.amt) AS AMT" + 
				"                                FROM incm_exp ia" + 
				"                               WHERE ia.incm_exp_date LIKE CONCAT(date_format(date_add(STR_TO_DATE(CONCAT(?, '01'), '%Y%m%d'), INTERVAL -1 MONTH), '%Y%m'), '%')" + 
				"                                 AND ia.id = ?" + 
				"                              GROUP BY ia.category_no) b" + 
				"    ON a.category_no = b.category_no" + 
				"  LEFT JOIN (SELECT ia.category_no, SUM(ia.amt) AS AMT" + 
				"                FROM incm_exp ia" + 
				"               WHERE ia.incm_exp_date LIKE CONCAT(?, '%')" + 
				"                 AND ia.id = ?" + 
				"              GROUP BY ia.category_no) c" + 
				"    ON a.category_no = c.category_no" + 
				" WHERE a.id = ?" + 
				"   AND a.incm_exp = ?";
	
	ArrayList<StatisticsBean> list = new ArrayList<StatisticsBean>();
	StatisticsBean bean = null;
	System.out.println(sql);
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, date);
		pstmt.setString(2, id);
		pstmt.setString(3, date);
		pstmt.setString(4, id);
		pstmt.setString(5, id);
		pstmt.setString(6, incmExp);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			bean = new StatisticsBean();
			bean.setCATEGORY_NO(rs.getInt("category_no"));
			bean.setCATEGORY_CONTENT(rs.getString("category_content"));
			bean.setINCM_EXP(rs.getString("incm_exp"));
			bean.setPRE_AMT(rs.getInt("pre_amt"));
			bean.setCUR_AMT(rs.getInt("cur_amt"));
			bean.setCOLOR(rs.getString("color"));
			
			list.add(bean);
		}
	}catch(Exception ex) {
		System.out.println("error : " + ex);
	}finally {
		close(rs);
		close(pstmt);
	}
	
	return list;
}

	
}
