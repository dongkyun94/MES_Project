package equipment.service;

public class ModifyEqMasterRequest {

	private String equip_cd;
	private String contents;
	private String use_yn;
	

	
	public ModifyEqMasterRequest(String equip_cd, String contents, String use_yn) {
		this.equip_cd = equip_cd;
		this.contents = contents;
		this.use_yn = use_yn;
	}

	public String getEquip_cd() {
		return equip_cd;
	}

	public String getContents() {
		return contents;
	}

	public String getUse_yn() {
		return use_yn;
	}
	
	
	

}

