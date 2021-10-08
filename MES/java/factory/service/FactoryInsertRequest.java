package factory.service;

import java.util.Map;


public class FactoryInsertRequest {

	private Integer comp_cd;
	private Integer plant_cd;
	private String plant_nm;
	private String valid_fr_dt;
	private String valid_to_dt;
	private String remark;
	private String in_usr_id;
	
	public FactoryInsertRequest(Integer comp_cd, Integer plant_cd, String plant_nm, String valid_fr_dt, String valid_to_dt,
			String remark, String in_usr_id) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.plant_nm = plant_nm;
		this.valid_fr_dt = valid_fr_dt;
		this.valid_to_dt = valid_to_dt;
		this.remark = remark;
		this.in_usr_id = in_usr_id;
	}

	public Integer getComp_cd() {
		return comp_cd;
	}
	
	
	
	

	public void setComp_cd(Integer comp_cd) {
		this.comp_cd = comp_cd;
	}
	
	
	
	

	public Integer getPlant_cd() {
		return plant_cd;
	}
	
	
	
	

	public void setPlant_cd(Integer plant_cd) {
		this.plant_cd = plant_cd;
	}
	
	
	
	

	public String getPlant_nm() {
		return plant_nm;
	}
	
	
	
	

	public void setPlant_nm(String plant_nm) {
		this.plant_nm = plant_nm;
	}
	
	
	
	

	public String getValid_fr_dt() {
		return valid_fr_dt;
	}
	
	
	
	

	public void setValid_fr_dt(String valid_fr_dt) {
		this.valid_fr_dt = valid_fr_dt;
	}
	
	
	
	

	public String getValid_to_dt() {
		return valid_to_dt;
	}
	
	
	
	

	public void setValid_to_dt(String valid_to_dt) {
		this.valid_to_dt = valid_to_dt;
	}
	
	
	
	

	public String getRemark() {
		return remark;
	}
	
	
	
	

	public void setRemark(String remark) {
		this.remark = remark;
	}
		
	public void validate(Map<String, Boolean> errors) {
		if(comp_cd == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(plant_cd == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
		
	}

	public String getIn_usr_id() {
		return in_usr_id;
	}

	public void setIn_usr_id(String in_usr_id) {
		this.in_usr_id = in_usr_id;
	}		

}
