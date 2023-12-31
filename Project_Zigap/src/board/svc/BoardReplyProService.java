package board.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import board.dao.BoardDAO;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean replyArticle(BoardBean article) throws Exception {
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		insertCount = boardDAO.insertReplyArticle(article);
		
		if(insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		}
		
		close(con);
		return isReplySuccess;
	}
}
