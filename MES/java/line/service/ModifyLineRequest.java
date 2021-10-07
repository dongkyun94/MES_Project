package line.service;

import java.util.Date;

public class ModifyLineRequest {
	
	private String line_cd;
	private String line_nm;
	private String use_yn;
	private String remark;
	private String up_usr_id;
	private Date up_date;

	
	public ModifyLineRequest(String line_cd, String line_nm, String use_yn,
			String remark, String up_usr_id, Date up_date) {
		this.line_cd = line_cd;
		this.line_nm = line_nm;
		this.use_yn = use_yn;
		this.remark = remark;
		this.up_usr_id = up_usr_id;
		this.up_date = up_date;
	}

	public String getLine_cd() {
		return line_cd;
	}

	public String getLine_nm() {
		return line_nm;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public String getRemark() {
		return remark;
	}

	public String getUp_usr_id() {
		return up_usr_id;
	}

	public Date getUp_date() {
		return up_date;
	}
	
	
	

}
