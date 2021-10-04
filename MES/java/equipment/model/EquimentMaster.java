package equipment.model;

import java.util.Date;

public class EquimentMaster {
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
	private String up_usr_id;
	private Date up_date;
	
	public EquimentMaster() {
		
	}
	
	public EquimentMaster(Integer comp_cd, Integer plant_cd, String line_cd, String equip_cd, Integer index_cd, 
			 String contents, String grade, String use_yn, String in_usr_id, Date in_date, String up_usr_id, Date up_date) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.line_cd = line_cd;
		this.equip_cd = equip_cd;
		this.index_cd = index_cd;
		this.contents = contents;
		this.grade = grade;
		this.use_yn = use_yn;
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

	public String equip_cd() {
		return equip_cd;
	}
	public Integer index_cd() {
		return index_cd;
	}
	
	public String contents() {
		return contents;
	}
	
	public String grade() {
		return grade;
	}
	
	public String getUse_yn() {
		return use_yn;
	}

	public String in_usr_id() {
		return in_usr_id;
	}

	public Date getIn_date() {
		return in_date;
	}

	public String up_usr_id() {
		return up_usr_id;
	}
	
	public Date getUp_date() {
		return up_date;
	}

	
}

