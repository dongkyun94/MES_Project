package line.service;


public class ModifyStatusRequest {
	
	private String line_cd;
	private String use_yn;
	
	public ModifyStatusRequest(String line_cd, String use_yn) {
		this.line_cd = line_cd;
		this.use_yn = use_yn;
	}

	public String getLine_cd() {
		return line_cd;
	}

	public String getUse_yn() {
		return use_yn;
	}


}
