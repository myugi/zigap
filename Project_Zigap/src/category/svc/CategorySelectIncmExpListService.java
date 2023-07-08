package category.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import category.dao.CategoryDAO;
import vo.CategoryBean;

public class CategorySelectIncmExpListService {
public List<CategoryBean> selectList(String id, String incmExp) throws Exception{
		
		List<CategoryBean> list = null;
		Connection con = getConnection();
		CategoryDAO categoryDAO = CategoryDAO.getInstance();
		categoryDAO.setConnection(con);
		list = categoryDAO.selectIncmExpList(id, incmExp);
		close(con);
		return list;
	}
}
