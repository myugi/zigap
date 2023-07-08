package vo;

public class AccountBean {

	private String BACCT_NO;
	private String BACCT_ID;
	private String BACCT_COMMENT;
	private String BANK_NAME;
	private int BASE_AMT;
	
	public String getBACCT_NO() {
		return BACCT_NO;
	}
	public void setBACCT_NO(String bACCT_NO) {
		BACCT_NO = bACCT_NO;
	}
	public String getBACCT_ID() {
		return BACCT_ID;
	}
	public void setBACCT_ID(String bACCT_ID) {
		BACCT_ID = bACCT_ID;
	}
	public String getBACCT_COMMENT() {
		return BACCT_COMMENT;
	}
	public void setBACCT_COMMENT(String bACCT_COMMENT) {
		BACCT_COMMENT = bACCT_COMMENT;
	}
	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}
	public int getBASE_AMT() {
		return BASE_AMT;
	}
	public void setBASE_AMT(int bASE_AMT) {
		BASE_AMT = bASE_AMT;
	}
}
