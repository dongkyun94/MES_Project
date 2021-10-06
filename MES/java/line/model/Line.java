package line.model;

import java.util.Date;

public class Line {
	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String line_nm;
	private String use_yn;
	private String remark;
	private String in_usr_id;
	private Date in_date;
	private String up_usr_id;
	private Date up_date;
	
	public Line() {
		
	}
	
	public Line(Integer comp_cd, Integer plant_cd, String line_cd, String line_nm, String use_yn, 
			String remark, String in_usr_id, Date in_date, String up_usr_id, Date up_date) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.line_nm = line_nm;
		this.use_yn = use_yn;
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

	public String getLine_cd() {
		return line_cd;
	}

	public String getLine_nm() {
		return line_nm;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public Date getIn_date() {
		return in_date;
	}

	public Date getUp_date() {
		return up_date;
	}

	public String getRemark() {
		return remark;
	}

	public String getIn_usr_id() {
		return in_usr_id;
	}

	public String getUp_usr_id() {
		return up_usr_id;
	}

	
}
