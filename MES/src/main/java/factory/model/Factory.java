package factory.model;

import java.util.Date;

public class Factory {
	private Integer comp_cd;
	private Integer plant_cd;
	private String plant_nm;
	private Date valid_fr_dt;
	private Date valid_to_dt;
	private String remark;
	private String in_usr_id;
	private Date in_date;
	private String up_usr_id;
	private Date up_date;
	
	public Factory(Integer comp_cd, Integer plant_cd, String plant_nm, Date valid_fr_dt, Date valid_to_dt,
			String remark, String in_usr_id, Date in_date, String up_usr_id, Date up_date) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.plant_nm = plant_nm;
		this.valid_fr_dt = valid_fr_dt;
		this.valid_to_dt = valid_to_dt;
		this.remark = remark;
		this.in_usr_id = in_usr_id;
		this.in_date = in_date;
		this.up_usr_id = up_usr_id;
		this.up_date = up_date;
	}

	public Integer getComp_cd() {
		return comp_cd;
	}

	public Integer getPlant_cd() {
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

	public String getRemark() {
		return remark;
	}

	public String getIn_usr_id() {
		return in_usr_id;
	}

	public Date getIn_date() {
		return in_date;
	}

	public String getUp_usr_id() {
		return up_usr_id;
	}

	public Date getUp_date() {
		return up_date;
	}
	
	
	
}
