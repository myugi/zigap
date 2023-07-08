package vo;

import java.sql.Date;

public class CalBean {

	private int SEQUENCE_NO;
	private String ID;
	private String INCM_EXP;
	private int CATEGORY_NO;
	private String BACCT_NO;
	private String INCM_EXP_DATE;
	private int AMT;
	private String COMMENT;
	private Date UPD_DATE;
	private String start;
	private String title;
	private String color;
	private String CATEGORY_CONTENT;
	private String BANK_NAME;
	
	public int getSEQUENCE_NO() {
		return SEQUENCE_NO;
	}
	public void setSEQUENCE_NO(int sEQUENCE_NO) {
		SEQUENCE_NO = sEQUENCE_NO;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getINCM_EXP() {
		return INCM_EXP;
	}
	public void setINCM_EXP(String iNCM_EXP) {
		INCM_EXP = iNCM_EXP;
	}
	public int getCATEGORY_NO() {
		return CATEGORY_NO;
	}
	public void setCATEGORY_NO(int cATEGORY_NO) {
		CATEGORY_NO = cATEGORY_NO;
	}
	public String getBACCT_NO() {
		return BACCT_NO;
	}
	public void setBACCT_NO(String bACCT_NO) {
		BACCT_NO = bACCT_NO;
	}
	public String getINCM_EXP_DATE() {
		return INCM_EXP_DATE;
	}
	public void setINCM_EXP_DATE(String iNCM_EXP_DATE) {
		INCM_EXP_DATE = iNCM_EXP_DATE;
	}
	public int getAMT() {
		return AMT;
	}
	public void setAMT(int aMT) {
		AMT = aMT;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public Date getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(Date uPD_DATE) {
		UPD_DATE = uPD_DATE;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCATEGORY_CONTENT() {
		return CATEGORY_CONTENT;
	}
	public void setCATEGORY_CONTENT(String cATEGORY_CONTENT) {
		CATEGORY_CONTENT = cATEGORY_CONTENT;
	}
	public String getBANK_NAME() {
		return BANK_NAME;
	}
	public void setBANK_NAME(String bANK_NAME) {
		BANK_NAME = bANK_NAME;
	}

}
