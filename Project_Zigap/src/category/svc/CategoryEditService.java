package category.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import category.dao.CategoryDAO;
import vo.CategoryBean;

public class CategoryEditService {
	public CategoryBean selectAccount(String id, String categoryNo) throws Exception{
		
		CategoryBean categoryBean = null;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		categoryBean = categoryDAO.selectCategory(id, categoryNo);
		close(con);
		return categoryBean;
	}

	
	public boolean deleteCategory(String id, String categoryNo) throws Exception{
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		int deleteCount = categoryDAO.deleteCategory(id, categoryNo);
		
		if(deleteCount > 0) {
			commit(con);
			isWriteSuccess = true;
		}
		else {
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}
}
