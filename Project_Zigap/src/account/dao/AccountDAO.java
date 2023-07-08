package account.dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.AccountBean;

public class AccountDAO {

	DataSource ds;
	Connection con;
	private static AccountDAO accountDAO;
	
	private AccountDAO() {
		
	}
	
	public static AccountDAO getInstance() {
		if(accountDAO == null) {
			accountDAO = new AccountDAO();
		}
		return accountDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	//은행명 불러오기
	public String getBankNm(String bacctNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM account WHERE bacct_no = ?";
		String bankNm = "";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bacctNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bankNm = rs.getString("bank_name");
			}
		}catch(Exception ex) {
			System.out.println("accountSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return bankNm;
	}
	
	//계좌목록보기
	public ArrayList<AccountBean> accountList(String id){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String account_list_sql = "SELECT * FROM account WHERE id=? ORDER BY crt_dt";
		ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
		AccountBean account = null;
		
		try {
			pstmt = con.prepareStatement(account_list_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account = new AccountBean();
				account.setBACCT_NO(rs.getString("bacct_no"));
				account.setBACCT_ID(rs.getString("id"));
				account.setBACCT_COMMENT(rs.getString("bacct_comment"));
				account.setBANK_NAME(rs.getString("bank_name"));
				account.setBASE_AMT(rs.getInt("base_amt"));
				accountList.add(account);
			}
		}catch(Exception ex) {
			System.out.println("getMemberList 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return accountList;
	}
	
	//계좌 등록
	public int insertAccount(AccountBean account) {
		
		PreparedStatement pstmt = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into account(BACCT_NO, ID, BACCT_COMMENT, BANK_NAME, BASE_AMT) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account.getBACCT_NO());
			pstmt.setString(2, account.getBACCT_ID());
			pstmt.setString(3, account.getBACCT_COMMENT());
			pstmt.setString(4, account.getBANK_NAME());
			pstmt.setInt(5, account.getBASE_AMT());
			
			insertCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountInsert 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return insertCount;
	}
		
	//조회하기
	public AccountBean selectAccount(String id, String bacctNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String account_list_sql = "SELECT * FROM account WHERE id=? AND bacct_no = ?";
		
		AccountBean account = null;
		
		try {
			pstmt = con.prepareStatement(account_list_sql);
			pstmt.setString(1, id);
			pstmt.setString(2, bacctNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account = new AccountBean();
				account.setBACCT_NO(rs.getString("bacct_no"));
				account.setBACCT_ID(rs.getString("id"));
				account.setBACCT_COMMENT(rs.getString("bacct_comment"));
				account.setBANK_NAME(rs.getString("bank_name"));
				account.setBASE_AMT(rs.getInt("base_amt"));
			}
		}catch(Exception ex) {
			System.out.println("accountSelect 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return account;
	}
	
	//계좌정보 수정
	public int updateAccount(AccountBean account) {
		PreparedStatement pstmt = null;
		String sql="";
		int updateCount = 0;
		
		try {
			sql = "UPDATE account SET bacct_comment=?, bank_name=?, base_amt=? WHERE id = ? AND bacct_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account.getBACCT_COMMENT());
			pstmt.setString(2, account.getBANK_NAME());
			pstmt.setInt(3, account.getBASE_AMT());
			pstmt.setString(4, account.getBACCT_ID());
			pstmt.setString(5, account.getBACCT_NO());
			
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("accountInsert 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return updateCount;
	}
	
	//계좌정보 삭제
	public int deleteAccount(String id, String bacctNo) {
		PreparedStatement pstmt = null;
		String sql = "";
		int deleteCount = 0;
		
		try {
			sql = "delete from account where id = ? and bacct_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, bacctNo);
			
			deleteCount = pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("accountDelete 에러 : " + ex);
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

		
}
