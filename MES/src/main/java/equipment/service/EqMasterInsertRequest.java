package equipment.service;

import java.util.Date;
import java.util.Map;

public class EqMasterInsertRequest {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String line_cd;
	private String equip_cd;
	private Integer index_cd;
	private String contents;
	private String grade;
	private String use_yn;
	private String in_usr_id;
	private Date in_date;

	
	public EqMasterInsertRequest(Integer comp_cd, Integer plant_cd, String line_cd, String equip_cd, Integer index_cd, 
			 String contents, String grade, String use_yn, String in_usr_id, Date in_date ) {
		
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.equip_cd = equip_cd;
		this.index_cd = index_cd;
		this.contents = contents;
		this.grade = grade;
		this.use_yn = use_yn;
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


	public String getLine_cd() {
		return line_cd;
	}
	public void setLine_cd(String line_cd) {
		this.line_cd = line_cd;
	}


	public String getequip_cd() {
		return equip_cd;
	}
	public void setEquip_cd(String equip_cd) {
		this.equip_cd = equip_cd;
	}


	public Integer getIndex_cd() {
		return index_cd;
	}
	public void setindex_cd(Integer index_cd) {
		this.index_cd = index_cd;
	}
	
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}


	public String getIn_usr_id() {
		return in_usr_id;
	}
	public void setIn_usr_id(String in_usr_id) {
		this.in_usr_id = in_usr_id;
	}


	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
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

