package category.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import category.dao.CategoryDAO;
import vo.CategoryBean;

public class CategoryListService {
	
	public ArrayList<CategoryBean> getCategoryList(String id) throws Exception{
		
		ArrayList<CategoryBean> categoryList = null;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		categoryList = categoryDAO.categoryList(id);
		close(con);
		return categoryList;
	}
	
	
	public String getCategoryNm(int categoryNo) throws Exception{
		
		String categoryNm = null;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		categoryNm = categoryDAO.getCategoryNm(categoryNo);
		close(con);
		return categoryNm;
	}
}
