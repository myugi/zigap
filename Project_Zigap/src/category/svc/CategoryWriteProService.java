package category.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import category.dao.CategoryDAO;
import vo.CategoryBean;


public class CategoryWriteProService {
	
	public int getMaxCount() throws Exception {
		
		int maxCount = 0;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		maxCount = categoryDAO.selectMaxCount();
		close(con);
		return maxCount;
		
	}
	
	public boolean registCategory(CategoryBean categoryBean) throws Exception {
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		int insertCount = categoryDAO.insertCategory(categoryBean);
		
		if(insertCount > 0) {
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

