package line.service;

import java.util.Date;
import java.util.Map;

import line.model.Line;

public class LineInsertRequest {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String line_nm;
	private String use_yn;
	private String in_date;
	
	public LineInsertRequest(Integer comp_cd, Integer plant_cd, String line_cd, String line_nm, String use_yn, 
			String in_date ) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.line_nm = line_nm;
		this.use_yn = use_yn;
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





	public String getLine_cd() {
		return line_cd;
	}





	public void setLine_cd(String line_cd) {
		this.line_cd = line_cd;
	}





	public String getLine_nm() {
		return line_nm;
	}





	public void setLine_nm(String line_nm) {
		this.line_nm = line_nm;
	}




	public String getUse_yn() {
		return use_yn;
	}





	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
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
		if(line_cd == null) {
			errors.put("line_cd", Boolean.TRUE);
		}
		
	}

}
