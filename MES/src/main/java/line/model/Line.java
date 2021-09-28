package line.model;

import java.util.Date;

public class Line {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String line_nm;
	private String use_yn;
	private Date in_date;
	private Date up_date;
	
	public Line() {
		
	}
	
	public Line(Integer comp_cd, Integer plant_cd, String line_cd, String line_nm, String use_yn, 
			Date in_date, Date up_date) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.line_nm = line_nm;
		this.use_yn = use_yn;
		this.in_date = in_date;
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

	
}
