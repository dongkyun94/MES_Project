package factory.service;

import java.util.Date;
import java.util.Map;

import factory.model.Factory;

public class FactoryInsertRequest {

	private Integer comp_cd;
	private Integer plant_cd;
	private String plant_nm;
	private String valid_fr_dt;
	private String valid_to_dt;
	private String remark;
	private String in_date;
	
	public FactoryInsertRequest(Integer comp_cd, Integer plant_cd, String plant_nm, String valid_fr_dt, String valid_to_dt,
			String remark, String in_date) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.plant_nm = plant_nm;
		this.valid_fr_dt = valid_fr_dt;
		this.valid_to_dt = valid_to_dt;
		this.remark = remark;
		this.in_date = in_date;		
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

	
	
	
	
	public String getIn_date() {
		return in_date;
	}
	
	
	
	

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}	
	
	
	public void validate(Map<String, Boolean> errors) {
		if(comp_cd == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(plant_cd == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
		
	}		

}
