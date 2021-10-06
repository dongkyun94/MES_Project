package factory.service;

import java.util.Date;

public class ModifyFactoryRequest {
	
	private int plant_cd;
	private String plant_nm;
	private Date valid_fr_dt;
	private Date valid_to_dt;
	private String up_usr_id;
	
	public ModifyFactoryRequest(int plant_cd, String plant_nm, Date valid_fr_dt, Date valid_to_dt, String up_usr_id) {
		this.plant_cd = plant_cd;
		this.plant_nm = plant_nm;
		this.valid_fr_dt = valid_fr_dt;
		this.valid_to_dt = valid_to_dt;

	}

	public int getPlant_cd() {
		return plant_cd;
	}

	public String getPlant_nm() {
		return plant_nm;
	}

	public Date getValid_fr_dt() {
		return valid_fr_dt;
	}

	public Date getValid_to_dt() {
		return valid_to_dt;
	}

	public String getUp_usr_id() {
		return up_usr_id;
	}
	
	

}
