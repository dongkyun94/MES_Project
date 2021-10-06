package line.service;

import java.util.Date;

public class ModifyLineRequest {
	
	private String line_cd;
	private String line_nm;
	private String use_yn;

	
	public ModifyLineRequest(String line_cd, String line_nm, String use_yn) {
		this.line_cd = line_cd;
		this.line_nm = line_nm;
		this.use_yn = use_yn;
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
	
	
	

}
