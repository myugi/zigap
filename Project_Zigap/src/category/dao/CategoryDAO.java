package category.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.CategoryBean;

public class CategoryDAO {
	
	DataSource ds;
	Connection con;
	private static CategoryDAO categoryDAO;
	
	private CategoryDAO() {
		
	}
	
	public static CategoryDAO getInstance() {
		if(categoryDAO == null) {
			categoryDAO = new CategoryDAO();
		}
		return categoryDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//수입/지출에 따른 카테고리 목록 구하기
	public List<CategoryBean> selectIncmExpList(String id, String incmExp){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String category_list_sql = "SELECT category_no, category_content FROM category WHERE id=? AND incm_exp=?";
		List list = new ArrayList<CategoryBean>();
		CategoryBean category = null;
		
		try {
			pstmt = con.prepareStatement(category_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, incmExp);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				category = new CategoryBean();
				category.setCATEGORY_NO(rs.getInt("category_no"));
				category.setCATEGORY_CONTENT(rs.getString("category_content"));
				list.add(category);
			}
		}catch(Exception ex) {
			System.out.println("getMemberList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	//카테고리의 개수 구하기
	public int selectMaxCount() {
		int maxCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String max_count_sql = "SELECT IFNULL(MAX(category_no),0)+1 FROM category";
		
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
	
	//카테고리목록보기
	public ArrayList<CategoryBean> categoryList(String id){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String category_list_sql = "SELECT * FROM category WHERE id=? ORDER BY incm_exp";
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryBean category = null;
		
		try {
			pstmt = con.prepareStatement(category_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				category = new CategoryBean();
				category.setCATEGORY_NO(rs.getInt("category_no"));
				category.setCATEGORY_ID(rs.getString("id"));
				category.setCATEGORY_CONTENT(rs.getString("category_content"));
				category.setINCM_EXP(rs.getString("incm_exp"));
				categoryList.add(category);
			}
		}catch(Exception ex) {
			System.out.println("getMemberList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return categoryList;
	}	
	
	//카테고리이름불러오기
	public String getCategoryNm(int categoryNo){
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String category_list_sql = "SELECT * FROM category WHERE category_no=?";
			String categoryNm = "";
			
			try {
				pstmt = con.prepareStatement(category_list_sql);
				pstmt.setInt(1, categoryNo);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					categoryNm = rs.getString("category_content");
				}
			}catch(Exception ex) {
				System.out.println("getMemberList 에러 : " + ex);
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return categoryNm;
		}	
	
	//카테고리 등록
		public int insertCategory(CategoryBean category) {
			
			PreparedStatement pstmt = null;
			String sql = "";
			int insertCount = 0;
			
			try {
				sql = "insert into category(CATEGORY_NO, ID, CATEGORY_CONTENT, INCM_EXP) values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, category.getCATEGORY_NO());
				pstmt.setString(2, category.getCATEGORY_ID());
				pstmt.setString(3, category.getCATEGORY_CONTENT());
				pstmt.setString(4, category.getINCM_EXP());
				
				insertCount = pstmt.executeUpdate();
				
			}catch(Exception ex) {
				System.out.println("accountInsert 에러 : " + ex);
			}finally {
				close(pstmt);
			}
			return insertCount;
		}
	
	//조회하기
	public CategoryBean selectCategory(String id, String categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CategoryBean category = null;
		String category_list_sql = "SELECT * FROM category WHERE id=? AND category_no = ?";

		try {
			pstmt = con.prepareStatement(category_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, categoryNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				category = new CategoryBean();
				category.setCATEGORY_NO(rs.getInt("category_no"));
				category.setCATEGORY_ID(rs.getString("id"));
				category.setCATEGORY_CONTENT(rs.getString("category_content"));
				category.setINCM_EXP(rs.getString("incm_exp"));
			}
		}catch(Exception ex) {
			System.out.println("accountSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return category;
	}
	
	//카테고리 삭제
	public int deleteCategory(String id, String categoryNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCount = 0;
		
		try {
			sql = "delete from category where id = ? and category_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, categoryNo);
			
			deleteCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountDelete 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

}
